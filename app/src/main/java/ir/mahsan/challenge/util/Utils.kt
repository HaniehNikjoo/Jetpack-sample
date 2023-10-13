package ir.mahsan.challenge.util

import com.google.gson.Gson
import ir.mahsan.challenge.model.dto.ErrorResponse
import retrofit2.HttpException


object Utils {
    fun getMessageError(it: Throwable): String {
        val errorResponse = Gson().fromJson(
            (it as? HttpException)?.response()?.errorBody()?.string(),
            ErrorResponse::class.java
        )
        return errorResponse?.message ?: it.message.toString()
    }

    private fun getDomainName(url: String): String {
        val domain = url.replace("http://", "").replace("https://", "").replace("www.", "")
        return domain.substring(0, domain.indexOf("."))
    }

    fun getAuthorValue(author: String?): String {
        if(author.isNullOrEmpty()) return "unknown"
        return if(author.contains("https://")) this.getDomainName(author) else author
    }
}