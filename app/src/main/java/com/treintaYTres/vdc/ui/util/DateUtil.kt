package com.treintaYTres.vdc.ui.util

import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(this))
}

fun Date.toText(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}

fun String.toDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale("es")).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return formatter.parse(this)
}

fun Int.formatTime(): String = this.toString().padStart(2,'0')

fun getTodayFormatted() = Date().toText()


fun PrettyTime.toText(date: String): String {
    val dateTime = date.toDate()
    return this.format(dateTime).replaceFirstChar {
        it.uppercase()
    }
}