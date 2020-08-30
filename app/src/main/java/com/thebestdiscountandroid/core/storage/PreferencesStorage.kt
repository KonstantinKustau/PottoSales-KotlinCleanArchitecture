package com.thebestdiscountandroid.core.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesStorage
@Inject constructor(private val context: Context) {

    private val key = "preferences_storage"

    private val userId = "userId"

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(key, MODE_PRIVATE)
    }

    private fun getEditor(): SharedPreferences.Editor {
        return getSharedPreferences().edit()
    }

    //TODO set it after logout
    fun setUserIdToZero() {
        getEditor().putInt(userId, 0).apply()
    }

    //TODO set it after first registration or sign in, get userId from server for this case
    fun setUserId(id: Int) {
        getEditor().putInt(userId, id).apply()
    }

    fun getUserId(): Int {
        return getSharedPreferences().getInt(userId, 0)
    }
}