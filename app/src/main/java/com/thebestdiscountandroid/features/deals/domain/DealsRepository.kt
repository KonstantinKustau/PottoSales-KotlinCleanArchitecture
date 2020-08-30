package com.thebestdiscountandroid.features.deals.domain

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.platform.BaseNetwork
import com.thebestdiscountandroid.core.platform.NetworkHandler
import com.thebestdiscountandroid.features.deals.data.DealsService
import javax.inject.Inject

interface DealsRepository {

    fun getTopDeals(userId: Int): Either<Failure, List<Deal>>

    fun filterProductByName(userId: Int, productName: String): Either<Failure, List<Deal>>

    class Network
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
                false, null -> Either.Left(Failure.NetworkConnection)
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
                false, null -> Either.Left(Failure.NetworkConnection)
            }
        }
    }
}