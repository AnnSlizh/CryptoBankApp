package by.slizh.cryptobankapp.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormat {
    @SuppressLint("ConstantLocale")
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    fun parseDate(dateStr: String): Date? {
        return try {
            dateFormat.parse(dateStr)
        } catch (e: ParseException) {
            null
        }
    }

    fun formatDate(date: Date): String {
        return dateFormat.format(date)
    }
}
