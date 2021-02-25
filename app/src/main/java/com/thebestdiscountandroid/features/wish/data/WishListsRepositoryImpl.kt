package com.thebestdiscountandroid.features.wish.data

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.platform.BaseNetwork
import com.thebestdiscountandroid.core.platform.NetworkHandler
import com.thebestdiscountandroid.features.wish.domain.WishListCategory
import com.thebestdiscountandroid.features.wish.domain.WishListUpdate
import com.thebestdiscountandroid.features.wish.domain.WishListUpdateEntity
import com.thebestdiscountandroid.features.wish.domain.WishListsRepository
import javax.inject.Inject

class WishListsRepositoryImpl
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
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
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
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }
}