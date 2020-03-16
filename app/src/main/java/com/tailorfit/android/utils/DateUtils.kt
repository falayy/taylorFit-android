package com.tailorfit.android.utils

import com.tailorfit.android.utils.DateUtils.FormatPattern.DISPLAY_DATE
import com.tailorfit.android.utils.DateUtils.FormatPattern.MYSQL_DATE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    object FormatPattern {
        const val MYSQL_DATE = "yyyy-MM-dd"
        const val DISPLAY_DATE = "dd MMMM yyyy"
    }

    fun formatDateToDisplayDate(date: String?): String? {
        val toFormat = SimpleDateFormat(DISPLAY_DATE, Locale.getDefault())
        val fromFormat = SimpleDateFormat(MYSQL_DATE, Locale.getDefault())
        return formatDate(date, fromFormat, toFormat)
    }

    fun formatDateToSQLDate(date: String?): String? {
        val toFormat = SimpleDateFormat(MYSQL_DATE, Locale.getDefault())
        val fromFormat = SimpleDateFormat(DISPLAY_DATE, Locale.getDefault())
        return formatDate(date, fromFormat, toFormat)
    }

    fun formatDate(date: String?, fromFormat: SimpleDateFormat, toFormat: SimpleDateFormat): String? {
        return try {
            toFormat.format(fromFormat.parse(date))
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }
}
