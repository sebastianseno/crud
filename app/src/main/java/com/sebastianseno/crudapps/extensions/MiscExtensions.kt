package com.sebastianseno.crudapps.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Long.toCurrency(currency: String = "", locale: Locale = Locale("id")): String {
    val formatter = NumberFormat.getCurrencyInstance(locale) as DecimalFormat
    with(formatter) {
        decimalFormatSymbols = decimalFormatSymbols.apply {
            currencySymbol = currency
        }
        maximumFractionDigits = 0
    }
    return formatter.format(this)
}

fun Long.toRupiah(currency: String = "Rp "): String {
    return toCurrency(currency)
}