package com.postcare.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class QuestionnaireViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val PREF_KEY = "QUESTION_ANSWER"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(application)

    private val _answer = MutableLiveData<String?>().apply {
        value = prefs.getString(PREF_KEY, null)
    }
    val answer: LiveData<String?> = _answer

    fun setAnswer(value: String) {
        _answer.value = value
    }

    fun saveAnswer() {
        _answer.value?.let { prefs.edit().putString(PREF_KEY, it).apply() }
    }
}
