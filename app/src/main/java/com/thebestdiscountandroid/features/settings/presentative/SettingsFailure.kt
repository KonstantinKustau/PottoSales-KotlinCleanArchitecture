package com.thebestdiscountandroid.features.settings.presentative

import com.thebestdiscountandroid.core.exception.Failure

class SettingsFailure {
    class LogoutFailure : Failure.FeatureFailure()
    class UpdateUserPropertiesFailure : Failure.FeatureFailure()
}
