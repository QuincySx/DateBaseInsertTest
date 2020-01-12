package utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {
    private val dateFormatThreadLocal: ThreadLocal<DateFormat> = object : ThreadLocal<DateFormat>() {
        override fun initialValue(): DateFormat? {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        }
    }

    fun format(date: Date): String {
        return dateFormatThreadLocal.get().format(date)
    }

    fun parse(date: String): Date {
        return dateFormatThreadLocal.get().parse(date)
    }
}