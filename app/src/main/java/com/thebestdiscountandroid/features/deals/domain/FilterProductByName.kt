package com.thebestdiscountandroid.features.deals.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.interactor.UseCase
import javax.inject.Inject

class FilterProductByName
@Inject constructor(private val dealsRepository: DealsRepository) :
    UseCase<List<DealEntity>, FilterProductByName.Params>() {

    data class Params(val userId: Int, val productName: String)

    override suspend fun run(params: Params): Either<Failure, List<DealEntity>> =
        dealsRepository.filterProductByName(params.userId, params.productName)
}