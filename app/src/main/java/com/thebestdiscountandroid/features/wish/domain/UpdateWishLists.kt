package com.thebestdiscountandroid.features.wish.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import com.thebestdiscountandroid.features.wish.domain.UpdateWishLists.Params
import javax.inject.Inject

class UpdateWishLists
@Inject constructor(private val wishListsRepository: WishListsRepository) :
    UseCase<WishListUpdateEntity, Params>() {

    data class Params(
        val userId: Int,
        val updateType: String,
        val updateData: List<WishListCategory>
    )

    override suspend fun run(params: Params): Either<Failure, WishListUpdateEntity> =
        wishListsRepository.updateWishLists(params.userId, params.updateType, params.updateData)
}