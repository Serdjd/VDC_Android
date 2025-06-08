package com.treintaYTres.vdc.viewmodel


import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.treintaYTres.vdc.util.getProfileUrl
import com.treintaYTres.vdc.util.toProfileUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefs: SharedPreferences
): ViewModel() {
    fun getProfileUrl() =( prefs.getProfileUrl() ?: "sergiodobladomunoz@gmail.com.png").toProfileUrl()
}