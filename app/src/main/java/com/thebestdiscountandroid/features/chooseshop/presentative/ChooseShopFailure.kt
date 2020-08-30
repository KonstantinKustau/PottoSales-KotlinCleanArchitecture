package com.thebestdiscountandroid.features.chooseshop.presentative

import com.thebestdiscountandroid.core.exception.Failure

class ChooseShopFailure {
    class ListNotAvailable : Failure.FeatureFailure()
    class LoadingLimitError : Failure.FeatureFailure()
}