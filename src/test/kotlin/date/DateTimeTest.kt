package date

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
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
        Assert.assertEquals(1, days)

        dateTime.addDays(2);
        days = dateTime.getDay()
        Assert.assertEquals(3, days)
    }

    @Test
    fun getMonth() {
        var months = dateTime.getMonth()
        Assert.assertEquals(1, months)

        dateTime.addMonths(2);
        months = dateTime.getMonth()
        Assert.assertEquals(3, months)
    }

    @Test
    fun getYear() {
    }

    @Test
    fun dayOfWeek() {
        dateTime.addYears(30)
//        dateTime.addMonths(6)
//        dateTime.addDays(24)

        val actual = dateTime.dayOfWeek()
        val expected = 5

        Assert.assertEquals(expected, actual)
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

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun parse() {
    }

    @Test
    fun toStringDate() {
    }

    @Test
    fun toUInt64() {
    }
}