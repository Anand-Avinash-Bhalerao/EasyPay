package com.core.common.pref.pspIP

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

data class PspIPDatastore (
    val datastore : DataStore<Preferences>
)