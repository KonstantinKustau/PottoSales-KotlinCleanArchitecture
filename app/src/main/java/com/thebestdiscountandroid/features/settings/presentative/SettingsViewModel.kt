package com.thebestdiscountandroid.features.settings.presentative

import androidx.lifecycle.MutableLiveData
import com.thebestdiscountandroid.core.platform.BaseViewModel
import com.thebestdiscountandroid.features.settings.domain.GetLogoutResult
import com.thebestdiscountandroid.features.settings.domain.SettingsUpdateEntity
import com.thebestdiscountandroid.features.settings.domain.UpdateUserProperties
import com.thebestdiscountandroid.features.settings.domain.UserLocalProperties
import javax.inject.Inject

class SettingsViewModel
@Inject constructor(
    private val getGetLogoutResult: GetLogoutResult,
    private val updateUserProperties: UpdateUserProperties
) : BaseViewModel() {

    var userProperties: MutableLiveData<SettingsUpdateEntity> = MutableLiveData()

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

    private fun handleUpdateUserProperties(settingsUpdate: SettingsUpdateEntity) {
        this.userProperties.value = settingsUpdate
    }

    private fun handleLogout(logoutResult: Any) {
        this.logoutResult.value = logoutResult
    }
}