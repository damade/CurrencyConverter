package com.damilola.core_android.ext

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.text.endsWith

fun getShortTimeForChat(dobString: String): String? {
    var date: Date? = null
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    try {
        date = sdf.parse(dobString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    if (date == null) return null
    val difference: Long
    val mDate = System.currentTimeMillis()
    val delta = date.time
    if (mDate > delta) {
        difference = mDate - delta
        val seconds = difference / 1000
        val nowTime = Calendar.getInstance()
        val neededTime = Calendar.getInstance()
        neededTime.timeInMillis = delta
        return if (seconds < 0) {
            null
        } else if (DateUtils.isToday(date.time)) {
            "Today"
        } else if ((neededTime[Calendar.YEAR] === nowTime[Calendar.YEAR]) &&
            (neededTime[Calendar.MONTH] === nowTime[Calendar.MONTH]) &&
            (nowTime[Calendar.DATE] - neededTime[Calendar.DATE] === 1)
        ) {
            "Yesterday"
        } else {
            chatTimeParser(dobString)
        }
    }
    return null
}

fun chatAdapterTimeParser(time: String): String {
    val format1 = SimpleDateFormat("hh:mm a")
    val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val date = format2.parse(time)
    return format1.format(date)
}

fun chatTimeParser(time: String): String {
    val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val firstDate = format2.parse(time)
    var format = SimpleDateFormat("d")
    val date = format.format(firstDate)

    format =
        if (date.endsWith("1") && !date.endsWith("11")) {
            SimpleDateFormat( "d'st' MMM, yyyy")
        } else if (date.endsWith("2") && !date.endsWith("12")) {
            SimpleDateFormat("d'nd' MMM, yyyy")
        } else if (date.endsWith("3") && !date.endsWith("13")) {
            SimpleDateFormat("d'rd' MMM, yyyy")
        } else SimpleDateFormat(
            "d'th' MMM, yyyy"
        )

    return  format.format(firstDate)
}