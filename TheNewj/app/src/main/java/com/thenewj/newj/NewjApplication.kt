package com.thenewj.newj

import android.app.Application
import androidx.preference.PreferenceManager
import com.thenewj.newj.utils.ThemeUtils
import com.thenewj.newj.utils.ThemeUtils.applyTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewjApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref = sharedPreferences.getString("themePref", ThemeUtils.DEFAULT_MODE)
        applyTheme(themePref ?: "")
    }
}