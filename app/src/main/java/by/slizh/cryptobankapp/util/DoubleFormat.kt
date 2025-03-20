package by.slizh.cryptobankapp.util

import java.util.Locale

object DoubleFormat {
    fun formatDouble(value: Double, decimalPlaces: Int = 2): String {
        return String.format(Locale.getDefault(), "%.${decimalPlaces}f", value)
    }

    fun formatPrice(value: Double): String {
        return "${formatDouble(value)} $"
    }
}
