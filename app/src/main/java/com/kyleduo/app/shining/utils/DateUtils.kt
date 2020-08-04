package com.kyleduo.app.shining.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val formatter = SimpleDateFormat("", Locale.CHINA)

    fun formatQualified(date: Date?): String {
        if (date == null) {
            return "-"
        }
        formatter.applyPattern("yyyy-MM-dd HH:mm:ss")
        return try {
            formatter.format(date)
        } catch (_: Exception) {
            "-"
        }
    }
}