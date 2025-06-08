package com.treintaYTres.vdc.util

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.core.content.edit
import com.treintaYTres.vdc.BuildConfig
import com.treintaYTres.vdc.navigation.screen.Screen
import com.treintaYTres.vdc.ui.model.auth.Validation
import com.treintaYTres.vdc.ui.util.Constant
import java.io.File

fun SharedPreferences.getUserId() = this.getInt(Constant.SharedPrefs.USER_ID, -1)

fun SharedPreferences.saveUserId(id: Int) {
    this.edit {
        putInt(Constant.SharedPrefs.USER_ID, id)
    }
}

fun SharedPreferences.getUserIsAdmin() = this.getBoolean(Constant.SharedPrefs.USER_IS_ADMIN, false)

fun SharedPreferences.updatePermissions(permissions: Int) {
    this.edit {
        putBoolean(Constant.SharedPrefs.USER_IS_ADMIN, permissions == 10)
    }
}

fun SharedPreferences.getProfileUrl() =
    this.getString(Constant.SharedPrefs.USER_URL, "")

fun SharedPreferences.updateProfileUrl(url: String) {
    this.edit {
        putString(Constant.SharedPrefs.USER_URL, url)
    }
}

fun Validation.navigate() =
    (
        if (registryCompleted) Screen.Root.Main
        else if (validate) Screen.Root.CompleteRegistry
        else if (isLogged) Screen.Root.Waiting
        else Screen.Root.Login
    ).route

fun String.toProfileUrl() = "${BuildConfig.API_PROFILE_URL}$this"
fun String.toInstrumentUrl() = "${BuildConfig.API_INSTRUMENT_URL}$this"
fun String.toStringUrl() = "${BuildConfig.API_STRING_URL}$this"

fun Uri.toFile(context: Context): File? {
    val inputStream = context.contentResolver.openInputStream(this)
    val tempFile = File.createTempFile("temp", ".png")
    return try {
        tempFile.outputStream().use { fileOut ->
            inputStream?.copyTo(fileOut)
        }
        tempFile.deleteOnExit()
        inputStream?.close()
        tempFile
    } catch (e: Exception) {
        null
    }
}