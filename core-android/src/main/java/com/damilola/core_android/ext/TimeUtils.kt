package com.damilola.core_android.ext


import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.floor

fun dateParser(time: String): String {
    val format1 = SimpleDateFormat("dd/MM/yyyy")
    val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val date = format2.parse(time)
    return format1.format(date)
}

fun hyphenSeparatedParser(time: String): String {
    val format1 = SimpleDateFormat("dd-MM-yyyy")
    val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date = format2.parse(time)
    return format1.format(date)
}

fun serverTimeToProfileTimeParser(time: String): String {
    val format1 = SimpleDateFormat("dd LLL yyyy")
    val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val date = format2.parse(time)
    return format1.format(date)
}

fun profileTimeToServerTimeParser(time: String): String {
    val format1 = SimpleDateFormat("yyyy-MM-dd")
    val format2 = SimpleDateFormat("dd LLL yyyy")
    val date = format2.parse(time)
    return format1.format(date)
}

fun timeParser(time: String): String {
    val format1 = SimpleDateFormat("hh:mm a")
    val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val date = format2.parse(time)
    return format1.format(date)
}

fun getAge(dobString: String): Int {
    var date: Date? = null
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    try {
        date = sdf.parse(dobString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    if (date == null) return 0
    val dob: Calendar = Calendar.getInstance()
    val today: Calendar = Calendar.getInstance()
    dob.time = date
    val year: Int = dob.get(Calendar.YEAR)
    val month: Int = dob.get(Calendar.MONTH)
    val day: Int = dob.get(Calendar.DAY_OF_MONTH)
    dob.set(year, month + 1, day)
    var age: Int = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }
    return age
}

fun getGreetingMessage(): String {
    val c = Calendar.getInstance()

    return when (c.get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> "Good Morning,"
        in 12..15 -> "Good Afternoon,"
        in 16..23 -> "Good Evening,"
        else -> "Hello"
    }
}

fun getDisplayableTime(dobString: String): String? {
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
        difference = mDate - delta - (60 * 1000 * 60)
        val seconds = difference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = days / 31
        val years = days / 365
        return if (seconds < 0) {
            "not yet"
        } else if (seconds < 60) {
            if (seconds == 1L) "one second ago" else "$seconds seconds ago"
        } else if (seconds < 120) {
            "a minute ago"
        } else if (seconds < 3600) // 60 * 60
        {
            "$minutes minutes ago"
        } else if (seconds < 7200) // 60 * 60 * 2
        {
            "an hour ago"
        } else if (seconds < 86400) // 24 * 60 * 60
        {
            "$hours hours ago"
        } else if (seconds < 172800) // 48 * 60 * 60
        {
            "yesterday"
        } else if (seconds < 601200) // (6 * 24 * 60 * 60) + (23* 60 * 60)
        {
            "$days days ago"
        } else if (seconds < 2332800) // ~ (3 * 7 * 24 * 60 * 60) + (6 * 24 * 60 * 60)
        {
            if (weeks <= 1) "1 week ago" else "$weeks weeks ago"
        } else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
        {
            if (months <= 1) "1 month ago" else "$days months ago"
        } else {
            if (years <= 1) "1 year ago" else "$years years ago"
        }
    }
    return null
}

fun getShortTime(dobString: String): String? {
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
        } else if (neededTime[Calendar.YEAR] === nowTime[Calendar.YEAR]) {
            if (neededTime[Calendar.MONTH] === nowTime[Calendar.MONTH]) {
                if (nowTime[Calendar.DATE] - neededTime[Calendar.DATE] === 1) {
                    "Yesterday"
                } else if (isThisWeek(date)) {
                    //If it is this week
                    "This Week"
                } else if (isLastWeek(date)) {
                    "Last Week"
                } else {
                    //If it is within a month
                    "This Month"
                }
            } else {
                //If it's a different month
                val months = neededTime[Calendar.MONTH] - nowTime[Calendar.MONTH]
                if (months <= 1) "One Month ago" else "$months Months ago"
            }
        } else {
            //If it's a different year
            val years = neededTime[Calendar.YEAR] - nowTime[Calendar.YEAR]
            if (years <= 1) {
                val months = (12 - neededTime[Calendar.MONTH]) + nowTime[Calendar.MONTH]
                if (months <= 1) "One Month ago" else "$months Months ago"
            } else {
                "$years Years ago"
            }
        }
    }
    return null
}

private fun isThisWeek(date: Date): Boolean {
    val c = Calendar.getInstance()
    c.firstDayOfWeek = Calendar.SUNDAY

    c[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
    c[Calendar.HOUR_OF_DAY] = 0
    c[Calendar.MINUTE] = 0
    c[Calendar.SECOND] = 0
    c[Calendar.MILLISECOND] = 0

    val sunday = c.time

    val nextSunday = Date(sunday.time + 7 * 24 * 60 * 60 * 1000)

    return date.after(sunday) && date.before(nextSunday)
}


private fun isLastWeek(date: Date): Boolean {
    val value: Boolean
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//        val event: LocalDate = Instant.ofEpochMilli(date.time).atZone(ZoneId.systemDefault()).toLocalDate()
//        val today = LocalDate.now()
//        val weekAgo: LocalDate = today.plusWeeks(-1)
//        value = (today >= event)  && (event > weekAgo)
//    }
    //else{
    val nowTime = Calendar.getInstance()
    val neededTime = Calendar.getInstance()
    neededTime.timeInMillis = date.time
    val daysSeperated = nowTime[Calendar.DAY_OF_WEEK] - neededTime[Calendar.DAY_OF_WEEK]
    val daysBetween = (nowTime[Calendar.DATE] - neededTime[Calendar.DATE]) - abs(daysSeperated)
    value = ((floor(((daysBetween / 7).toDouble()))) in 0.0..1.0) && ((daysBetween % 7) in 0..6)
    //}

    return value
}

fun transactionTimeParser(time: String): String {
    val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val firstDate = format2.parse(time)
    var format = SimpleDateFormat("d")
    val date = format.format(firstDate)

    format =
        if (date.endsWith("1") && !date.endsWith("11")) {
            SimpleDateFormat("d'st' LLL yyyy")
        } else if (date.endsWith("2") && !date.endsWith("12")) {
            SimpleDateFormat("d'nd' LLL yyyy")
        } else if (date.endsWith("3") && !date.endsWith("13")) {
            SimpleDateFormat("d'rd' LLL yyyy")
        } else SimpleDateFormat(
            "d'th' LLL yyyy"
        )

    return format.format(firstDate)
}

fun transactionFilterTimeParser(time: String?): String {
    try {
        val format2 = SimpleDateFormat("MM-dd-yyyy")
        val firstDate = format2.parse(time)
        var format = SimpleDateFormat("d")
        val date = format.format(firstDate)

        format =
            if (date.endsWith("1") && !date.endsWith("11")) {
                SimpleDateFormat("d'st' LLL yyyy")
            } else if (date.endsWith("2") && !date.endsWith("12")) {
                SimpleDateFormat("d'nd' LLL yyyy")
            } else if (date.endsWith("3") && !date.endsWith("13")) {
                SimpleDateFormat("d'rd' LLL yyyy")
            } else SimpleDateFormat(
                "d'th' LLL yyyy"
            )
        return format.format(firstDate)
    } catch (e: ParseException) {
        e.printStackTrace()
        return ""
    }
}

fun getDueDate(nextPayString: String?): Pair<String?, Boolean> {

    //Pair Boolean: Is Pay Due?
    var result = Pair("Something Went Wrong", false)
    var date: Date? = null
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    try {
        date = sdf.parse(nextPayString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    if (date == null) return result
    var difference: Long
    val mDate = System.currentTimeMillis()
    val delta = date.time
    difference = mDate - delta - (60 * 1000 * 60)
    val timePositive = difference > 0
    val verbToUse = if (timePositive) "Due since" else "Due in"
    result = result.copy(second =  timePositive)
    difference = abs(difference)
    val seconds = difference / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val months = days / 31
    val years = days / 365

    if (seconds < 60) {
        result = result.copy(first = if (seconds == 1L) "$verbToUse 1 second" else "$seconds seconds")
    } else if (seconds < 120) {
        result = result.copy(first = "$verbToUse a minute")
    } else if (seconds < 3600) // 60 * 60
    {
        result = result.copy(first = "$verbToUse $minutes minutes")
    } else if (seconds < 7200) // 60 * 60 * 2
    {
        result = result.copy(first = "$verbToUse an hour")
    } else if (seconds < 86400) // 24 * 60 * 60
    {
        result = result.copy(first = "$verbToUse $hours hours")
    } else if (seconds < 601200) // (6 * 24 * 60 * 60) + (23* 60 * 60)
    {
        result = result.copy(first = "$verbToUse $days days")
    } else if (seconds < 2332800) // ~ (3 * 7 * 24 * 60 * 60) + (6 * 24 * 60 * 60)
    {
        result = result.copy(first = if (weeks <= 1) "$verbToUse 1 week" else "$verbToUse $weeks weeks")
    } else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
    {
        result = result.copy(first = if (months <= 1) "$verbToUse 1 month" else "$verbToUse $days months")
    } else {
        result = result.copy(first = if (years <= 1) "$verbToUse 1 year" else "$verbToUse $years years")
    }
    return result
}