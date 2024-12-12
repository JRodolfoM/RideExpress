package br.com.jrmantovani.rideexpress.core

import com.google.android.material.textfield.TextInputLayout
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale


fun Int.toKm(): Double {
    return this.toDouble() / 1000
}



fun String.toFormattedDate(outputFormat: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val date = inputFormat.parse(this) ?: return "Data inválida"
    val outputFormatter = SimpleDateFormat(outputFormat)
    return outputFormatter.format(date)
}

fun Double.formatAsCurrency(
    locale: Locale = Locale("pt", "BR"),
    maxDecimalDigits: Int = 2,
    customCurrencySymbol: String = "R$"
): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(locale).apply {
        maximumFractionDigits = maxDecimalDigits
    }
    val currencySymbol = customCurrencySymbol ?: currencyFormat.currency?.symbol ?: ""

    val formatted = currencyFormat.format(this)
    return "$currencySymbol ${formatted.replace(currencyFormat.currency?.symbol ?: "", "").trim()}"
}

fun Double.formatAsKilometerWithUnit(decimalPlaces: Int = 2): String {
    return "%.${decimalPlaces}f km".format(this)
}

fun TextInputLayout.isValid(errorMessage: String): Boolean {
    val inputText = this.editText?.text.toString()
    return if (inputText.isBlank()) {
        this.error = errorMessage
        false
    } else {
        this.error = null
        true
    }
}