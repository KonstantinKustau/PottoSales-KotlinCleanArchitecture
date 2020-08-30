package com.thebestdiscountandroid.features.deals.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import javax.inject.Inject

class GetTopDeals
@Inject constructor(private val dealsRepository: DealsRepository) :
    UseCase<List<Deal>, GetTopDeals.Params>() {

    data class Params(val userId: Int)

    override suspend fun run(params: Params): Either<Failure, List<Deal>> =
        dealsRepository.getTopDeals(params.userId)

}