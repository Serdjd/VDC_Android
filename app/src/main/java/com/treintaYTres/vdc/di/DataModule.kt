package com.treintaYTres.vdc.di

import android.content.Context
import android.content.SharedPreferences
import com.treintaYTres.vdc.ui.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.ocpsoft.prettytime.PrettyTime
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun sharedPreferences(@ApplicationContext context: Context) = context
        .getSharedPreferences(
            Constant.SharedPrefs.USER_KEY,
            Context.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun editor(sharedPreferences: SharedPreferences) =
        sharedPreferences.edit()

    @Provides
    @Singleton
    fun prettyTime() = PrettyTime(Calendar.getInstance().time, Locale("es"))
}