package date

import org.junit.Assert.*
import org.junit.Test
import date.util.*
import org.junit.Before
import java.util.*

class DateTimeTest {
    private lateinit var dateTime: DateTime

    @Before
    fun setUp() {
        dateTime = DateTime(1970, 1, 1)
    }

    @Test
    fun getDay() {
        var days = dateTime.getDay()
        assertEquals(1, days)

        dateTime.addDays(2);
        days = dateTime.getDay()
        assertEquals(3, days)
    }

    @Test
    fun getMonth() {
        var months = dateTime.getMonth()
        assertEquals(1, months)

        dateTime.addMonths(2);
        months = dateTime.getMonth()
        assertEquals(3, months)
    }

    @Test
    fun getYear() {
    }

    @Test
    fun dayOfWeek() {
        dateTime.addYears(45)
        dateTime.addMonths(11)
        dateTime.addDays(9)

        val actual = dateTime.dayOfWeek()
        val expected = DayOfWeek.Thursday.ordinal

        assertEquals(expected, actual)
    }

    @Test
    fun validate() {
    }

    @Test
    fun addDays() {
    }

    @Test
    fun addMonths() {
    }

    @Test
    fun addYears() {
    }

    @Test
    fun getNow() {
        var now = dateTime.getNow()
        val expected = Date().time / 1000
        val actual = now.toUInt64()

        assertEquals(expected, actual)
    }

    @Test
    fun parse() {
        var actual = dateTime.parse("2020.02.01")
        var expected = DateTime(2020, 2, 28 * 86400 + 1)

        assertTrue(actual.dateTimeCmp(expected, actual) == 0)
    }

    @Test
    fun toStringDate() {
    }

    @Test
    fun toUInt64() {
    }
}