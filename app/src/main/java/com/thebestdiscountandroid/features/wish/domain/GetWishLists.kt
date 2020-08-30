package com.thebestdiscountandroid.features.wish.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import com.thebestdiscountandroid.features.wish.domain.GetWishLists.Params
import javax.inject.Inject

class GetWishLists
@Inject constructor(private val wishListsRepository: WishListsRepository) :
    UseCase<List<WishListCategory>, Params>() {

    data class Params(val userId: Int)

    override suspend fun run(params: Params): Either<Failure, List<WishListCategory>> =
        wishListsRepository.getWishLists(params.userId)
}