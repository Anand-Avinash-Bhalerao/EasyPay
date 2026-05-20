package com.core.common.pref.npciKeys

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

data class NpciKeyDatastore (
    val datastore: DataStore<Preferences>
)