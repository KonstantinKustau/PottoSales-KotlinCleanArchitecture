package com.thebestdiscountandroid.features.chooseshop.data

import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.functional.Either
import com.thebestdiscountandroid.core.platform.BaseNetwork
import com.thebestdiscountandroid.core.platform.NetworkHandler
import com.thebestdiscountandroid.features.chooseshop.domain.ChooseShopRepository
import com.thebestdiscountandroid.features.chooseshop.domain.ProductLimitEntity
import com.thebestdiscountandroid.features.chooseshop.domain.ShopEntity
import javax.inject.Inject

class ChooseShopRepositoryImpl
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: ChooseShopService
) : ChooseShopRepository, BaseNetwork() {

    override fun getShopsByProduct(productId: Int): Either<Failure, List<ShopEntity>> {
        return when (networkHandler.isConnected) {
            true -> request(
                service.getShopsByProduct(productId),
                { it.map { shopEntity -> shopEntity.toShop() } },
                emptyList()
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }

    override fun setLimitOnUserProduct(
        userId: Int,
        productName: String,
        limit: Double
    ): Either<Failure, Any> {
        return when (networkHandler.isConnected) {
            true -> request(
                service.setLimitOnUserProduct(userId, productName, limit),
                {},
                Any()
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }

    override fun getLimitsOnUser(userId: Int): Either<Failure, List<ProductLimitEntity>> {
        return when (networkHandler.isConnected) {
            true -> request(
                service.getLimitsOnUser(userId),
                { it.map { productLimitEntity -> productLimitEntity.toProductLimit() } },
                emptyList()
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }

}