package com.example.smartnotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.smartnotes.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Set up the "Back" button on the toolbar
        binding.toolbarSettings.setNavigationOnClickListener {
            finish() // Closes this activity and returns to MainActivity
        }

        // 2. Set the currently selected radio button
        updateRadioButton()

        // 3. Listen for changes in the RadioGroup
        binding.radioGroupLanguage.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton_english -> setAppLocale("en")
                R.id.radioButton_spanish -> setAppLocale("es")
            }
        }
    }

    private fun setAppLocale(languageCode: String) {
        // Create a new locale list with the chosen language
        val appLocale = LocaleListCompat.forLanguageTags(languageCode)

        // Set the app's locale
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    private fun updateRadioButton() {
        // Get the current app locale, or the system locale if not set
        val currentLocale = AppCompatDelegate.getApplicationLocales()[0]?.toLanguageTag() ?: "en"

        if (currentLocale.startsWith("es")) {
            binding.radioButtonSpanish.isChecked = true
        } else {
            binding.radioButtonEnglish.isChecked = true
        }
    }
}
