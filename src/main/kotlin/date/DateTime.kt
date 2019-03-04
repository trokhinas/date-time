package date

import date.util.DayOfWeek
import date.util.Months
import java.util.*
import kotlin.IllegalArgumentException


class DateTime(private var years: Int, private var months: Int, private var seconds: Long) : AbstractDateInterface {

    private val default20Code:Int
        get() = (6 + years % 100 + years % 100 / 4) % 7
    private val secondsInDay = 86400L
    private val secInMonth: Long get() = secondsInDay * daysInMonth
    private val monthInYear = 12
    private val daysInYear: Int get() = if (isLoopYear(years)) 366 else 365
    private val daysInMonth: Int
        get()  {
            val days = Months.values()[months - 1].days
            return if (isLoopYear(years) && months == 2) days + 1 else days
        }
    private val codeOfMonths: Int
            get() {
                val month =  Months.values().find { x -> x.ordinal == months - 1 }!!
                return month.code
    }




    override fun getDay(): Int {
        val result =  Math.ceil(
                seconds.toDouble() / secondsInDay.toDouble()
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
        var code = (getDay() + codeOfMonths + default20Code) % 7
        if(isLoopYear(years)) {
            if(getDay() < 29 && getMonth() <= 2)
                code += 1
        }
        return (DayOfWeek.values()[code].ordinal + 5) % 7
    }

    override fun validate(): Boolean {
        if (years < 0 ) return false
        if (months > monthInYear || months < 0) return false
        if (seconds < 0 || seconds > secInMonth) return false
        return true
    }

    override fun addDays(days: Int) {
        if(days < 0) throw IllegalArgumentException("Количество дней ($days) должно быть положительным!")
        val seconds = days * secondsInDay
        addSeconds(seconds)
    }
    override fun addMonths(months: Int) {
        if(months < 0) throw IllegalArgumentException("Количество месяцев ($months) должно быть положительным!")
        this.months += months

        while (this.months > monthInYear) {
            this.months -= monthInYear
            addYears(1)
        }
    }
    override fun addYears(years: Int) {
        if(years < 0) throw IllegalArgumentException("Количество лет ($years) должно быть положительным!")
        this.years += years
    }

    override fun getNow(): AbstractDateInterface {
        val realSeconds = Date().time / 1000

        val now = DateTime(1970, 1, 0)
        now.addSeconds(realSeconds)
        return now
    }

    /**
     * парсятся только строки по шаблону yyyy/MM/dd
     */
    override fun parse(date: String): AbstractDateInterface {
        val year = date.substring(0..3)
        val month = date.substring(5..6)
        val day = date.substring(8..9)

        return DateTime(year.toInt(), month.toInt(), day.toInt() * secondsInDay)
    }


    override fun toUInt64(): Long {
        var result = 0L
        for (year in 1970 until years) {
            result += dayInYear(year)
        }
        for (month in 1 until months) {
            result += dayInMonth(month)
        }
        result *= secondsInDay
        result += seconds
        return result
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
        var dayStr = getDay().toString()
        if(getDay() < 10) dayStr = "0$dayStr"
        var monthStr = getMonth().toString()
        if(getMonth() < 10) monthStr = "0$monthStr"
        return "$dayStr/$monthStr/${getYear()}"
    }
    override fun toStringDate(): String {
        return toString()
    }

    private fun addSeconds(i: Long) {
        seconds += i
        while (seconds > secondsInDay * daysInYear) {
            seconds -= secondsInDay * daysInYear
            addYears(1)
        }
        while (seconds > secondsInDay * daysInMonth) {
            seconds -= secondsInDay * daysInMonth
            addMonths(1)
        }
    }

    init {
        if (!validate()) throw IllegalArgumentException("В конструктор переданы некорректные параметры!")
    }


}