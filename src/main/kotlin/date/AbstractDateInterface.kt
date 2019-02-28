package date

interface AbstractDateInterface {
    fun getDay(): Int
    fun getMonth(): Int
    fun getYear(): Int
    fun dayOfWeek(): Int


    fun dateTimeCmp(a: AbstractDateInterface, b: AbstractDateInterface): Int {
        var result = 0
        result += (a.toUInt64() - b.toUInt64()).toInt()

        return result
    }

    fun validate(): Boolean

    fun addDays(days: Int)
    fun addMonths(months: Int)
    fun addYears(years: Int)


    fun getNow(): AbstractDateInterface
    fun parse(date: String): AbstractDateInterface

    fun toStringDate(): String

    fun toUInt64(): Long
}