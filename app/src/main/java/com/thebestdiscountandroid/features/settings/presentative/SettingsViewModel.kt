package com.thebestdiscountandroid.features.settings.presentative

import androidx.lifecycle.MutableLiveData
import com.thebestdiscountandroid.core.platform.BaseViewModel
import com.thebestdiscountandroid.features.settings.domain.GetLogoutResult
import com.thebestdiscountandroid.features.settings.domain.SettingsUpdate
import com.thebestdiscountandroid.features.settings.domain.UpdateUserProperties
import com.thebestdiscountandroid.features.settings.domain.UserLocalProperties
import com.thebestdiscountandroid.features.wish.domain.WishListCategory
import com.thebestdiscountandroid.features.wish.domain.WishListUpdate
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishCategoryView
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishProductView
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishView
import javax.inject.Inject

class SettingsViewModel
@Inject constructor(
    private val getGetLogoutResult: GetLogoutResult,
    private val updateUserProperties: UpdateUserProperties
) : BaseViewModel() {

    var userProperties: MutableLiveData<SettingsUpdate> = MutableLiveData()

    var logoutResult: MutableLiveData<Any> = MutableLiveData()

    fun updateUserProperties(
        userId: Int, userLocalProperties: UserLocalProperties
    ) {
        updateUserProperties(UpdateUserProperties.Params(userId, userLocalProperties)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleUpdateUserProperties(it) })
        }
    }

    fun logout(userId: Int) {
        getGetLogoutResult(GetLogoutResult.Params(userId)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleLogout(it) })
        }
    }

    private fun handleUpdateUserProperties(settingsUpdate: SettingsUpdate) {
        this.userProperties.value = settingsUpdate
    }

    private fun handleLogout(logoutResult: Any) {
        this.logoutResult.value = logoutResult
    }
}