package com.example.weatherforecast.common

import java.text.SimpleDateFormat
import java.util.*

fun String.isValidCity(): Boolean {
    val regex = "^([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$"
    val pattern = Regex(regex)
    return pattern.containsMatchIn(this.trim())
}

fun Long.toDDMMYYYHHMMFormat(): String {
    val simpleDateFormat = SimpleDateFormat("dd MMM yyyy: hh:MMM a", Locale.getDefault())
    return simpleDateFormat.format(Date(this))
}

fun Double.toCelcius(): Double {
    return this - 273.15
}