package date

import java.util.*



class DateTime : AbstractDateInterface {

    private var years: Int
    private var months: Int
    private var seconds: Int

    constructor(years: Int, months: Int, seconds: Int) {
        this.years = years
        this.months = months
        this.seconds = seconds
    }


    private val SECONDS_IN_DAY = 86400
    private val MONTH_IN_YEAR = 12
    private val daysInYear: Int
        get() = if (isLoopYear(years)) 366 else 365
    private val daysInMonth: Int
        get()  {
            val days = Months.values()[months - 1].days
            return if (isLoopYear(years) && months == 2) days + 1 else days
        }



    override fun getDay(): Int {
        val result =  Math.ceil(
                seconds.toDouble() / SECONDS_IN_DAY.toDouble()
        )
        return result.toInt()
    }
    override fun getMonth(): Int {
        return months
    }
    override fun getYear(): Int {
        return years
    }

    override fun dayOfWeek(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun validate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addDays(days: Int) {
        var days = days

        while (days > daysInYear) {
            days -= daysInYear
            addYears(1)
        }

        while (days > daysInMonth) {
            days -= daysInMonth
            addMonths(1)
        }

        seconds += days * SECONDS_IN_DAY
    }

    override fun addMonths(months: Int) {
        this.months += months

        years += months / MONTH_IN_YEAR
        this.months %= MONTH_IN_YEAR;
    }

    override fun addYears(years: Int) {
        this.years += years
    }

    override fun getNow(): AbstractDateInterface {
        val now = DateTime(1970, 1, 0)
        val sec = Date().time
        var days = Math.ceil(sec.toDouble() / SECONDS_IN_DAY / 1000)

        while (days > now.daysInYear) {
            days -= now.daysInYear
            now.addYears(1)
        }
        while (days > now.daysInMonth) {
            days -= now.daysInMonth
            now.addMonths(1)
        }

        now.seconds += (days.toInt() * SECONDS_IN_DAY)

        return now
    }

    override fun parse(date: String): AbstractDateInterface {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toStringDate(): String {
        return toString()
    }

    override fun toUInt64(): Long {
        var days = 0
        val fake = DateTime(years, months, seconds)
        while (fake.years > 1970) {
            days += fake.dayInYear(fake.years - 1)
            fake.years --
        }
        while (fake.months > 1) {
            days += fake.dayInMonth(fake.months - 1)
            fake.months --
        }
        return (fake.seconds + days * SECONDS_IN_DAY).toLong()
    }

    private fun dayInYear(year: Int): Int {
        return if (isLoopYear(year)) 366 else 365
    }
    private fun dayInMonth(month: Int): Int {
        val days = Months.values()[month - 1].days
        return if (isLoopYear(years) && month == 2) days + 1 else days
    }
    private fun isLoopYear(year: Int): Boolean {
        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)
    }

    override fun toString(): String {
        return "${getDay()}/${getMonth()}/${getYear()}"
    }
}