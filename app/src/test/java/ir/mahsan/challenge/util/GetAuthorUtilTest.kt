package ir.mahsan.challenge.util

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class GetAuthorUtilTest {

    @Test
    fun getAuthor_null_returnsUnknownStr() {
        val author = null

        MatcherAssert.assertThat(getAuthorValue(author), Matchers.`is`("unknown"))
    }

    @Test
    fun getAuthor_url_returnsDomainName() {
        val author = "https://facebook.com"

        MatcherAssert.assertThat(getAuthorValue(author), Matchers.`is`("facebook"))
    }

    @Test
    fun getAuthor_email_returnsName() {
        val author = "d@wisestamp.com"

        MatcherAssert.assertThat(getAuthorValue(author), Matchers.`is`("wisestamp"))
    }

}