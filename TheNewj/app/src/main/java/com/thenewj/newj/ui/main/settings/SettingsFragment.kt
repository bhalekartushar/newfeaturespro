package com.thenewj.newj.ui.main.settings

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.thenewj.newj.R
import com.thenewj.newj.utils.ThemeUtils.applyTheme

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val themePreference = findPreference<ListPreference>("themePref")
        themePreference?.let {
            themePreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference: Preference?, newValue: Any? ->
                    newValue?.let {
                        val themeOption = newValue as String
                        themeOption?.let {
                            applyTheme(themeOption)
                        }
                    }
                    true
                }
        }
    }

    companion object {
        const val TAG = "SettingsFragment"
    }
}