package com.example.weatherforecast.common

import java.text.SimpleDateFormat
import java.util.*

fun String.isValidCity(): Boolean {
    return if (this.isBlank().not()) {
        val regex = "^([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$"
        val pattern = Regex(regex)
        pattern.containsMatchIn(this.trim())
    } else {
        false
    }
}

fun Long.toDDMMYYYHHMMFormat(): String {
    val simpleDateFormat = SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault())
    return simpleDateFormat.format(Date(this * 1000L))
}

fun Double.toCelcius(): String {
    val conversion = this - 273.15
    return conversion.toTwoDigits()
}

fun Double.toTwoDigits(): String {
    return String.format("%.2f",this)
}