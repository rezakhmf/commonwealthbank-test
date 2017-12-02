package com.reza.commbank.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by reza on 2/12/17.
 */
class DateProvider {
    companion object {
        fun dayDiffWithLable(dateStr: String) : String {
            val sdf = SimpleDateFormat("dd/mm/yyyy")
            val targetDate = sdf.parse(dateStr)
            val diff = Date().getTime() - targetDate.getTime()
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toString() + " days ago"
        }

        fun dateWithMonthLabel(dateStr: String ) : String {
            val sdf = SimpleDateFormat("dd/mm/yyyy")
            val month_date = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
            val targetDate = sdf.parse(dateStr)
            val month_name = month_date.format(targetDate)
            return month_name
        }
    }
}