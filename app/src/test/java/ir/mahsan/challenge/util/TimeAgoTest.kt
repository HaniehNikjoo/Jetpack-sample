package ir.mahsan.challenge.util

import java.text.SimpleDateFormat
import java.util.Date
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test
import java.util.TimeZone

class TimeAgoTest {

    @Test
    fun getTime_null_returnsEmptyStr() {
        val time = ""

        assertThat(timeAgo(time), Matchers.`is`(""))
    }

    @Test
    fun getTime_current_returnsOneSecondAgo() {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val currentDate = sdf.format(Date())

        assertThat(timeAgo(currentDate), Matchers.`is`("1 Seconds Ago"))
    }
}