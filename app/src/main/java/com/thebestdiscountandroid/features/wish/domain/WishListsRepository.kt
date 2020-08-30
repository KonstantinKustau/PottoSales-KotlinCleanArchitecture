package com.thebestdiscountandroid.features.wish.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.exception.Failure.NetworkConnection
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.functional.Either.Left
import com.thebestdiscountandroid.core.platform.BaseNetwork
import com.thebestdiscountandroid.core.platform.NetworkHandler
import com.thebestdiscountandroid.features.wish.data.WishListsService
import javax.inject.Inject

interface WishListsRepository {

    fun getWishLists(userId: Int): Either<Failure, List<WishListCategory>>

    fun updateWishLists(
        userId: Int,
        updateType: String,
        updateData: List<WishListCategory>
    ): Either<Failure, WishListUpdate>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: WishListsService
    ) : WishListsRepository, BaseNetwork() {

        override fun getWishLists(userId: Int): Either<Failure, List<WishListCategory>> {
            return when (networkHandler.isConnected) {
                true -> request(
                    service.getWishLists(userId),
                    { it.map { wishListCategoryEntity -> wishListCategoryEntity.toWishListCategory() } },
                    emptyList()
                )
                false, null -> Left(NetworkConnection)
            }
        }

        override fun updateWishLists(
            userId: Int,
            updateType: String,
            updateData: List<WishListCategory>
        ): Either<Failure, WishListUpdate> {
            return when (networkHandler.isConnected) {
                true -> request(
                    service.updateWishLists(userId, updateType, updateData),
                    { wishListUpdateEntity -> wishListUpdateEntity.toWishListUpdate() },
                    WishListUpdateEntity.empty()
                )
                false, null -> Left(NetworkConnection)
            }
        }
    }
}