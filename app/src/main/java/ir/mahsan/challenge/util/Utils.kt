package ir.mahsan.challenge.util

import android.util.Log
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
}