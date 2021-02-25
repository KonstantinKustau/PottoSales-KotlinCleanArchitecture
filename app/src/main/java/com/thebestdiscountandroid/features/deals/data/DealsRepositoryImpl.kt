package com.thebestdiscountandroid.features.deals.data

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.platform.BaseNetwork
import com.thebestdiscountandroid.core.platform.NetworkHandler
import com.thebestdiscountandroid.features.deals.domain.Deal
import com.thebestdiscountandroid.features.deals.domain.DealsRepository
import javax.inject.Inject

class DealsRepositoryImpl
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: DealsService
) : DealsRepository, BaseNetwork() {

    override fun getTopDeals(userId: Int): Either<Failure, List<Deal>> {
        return when (networkHandler.isConnected) {
            true -> request(
                service.getTopDeals(userId),
                { it.map { dealEntity -> dealEntity.toDeal() } },
                emptyList()
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }

    override fun filterProductByName(
        userId: Int,
        productName: String
    ): Either<Failure, List<Deal>> {
        return when (networkHandler.isConnected) {
            true -> request(
                service.filterProductByName(userId, productName),
                { it.map { dealEntity -> dealEntity.toDeal() } },
                emptyList()
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }
}