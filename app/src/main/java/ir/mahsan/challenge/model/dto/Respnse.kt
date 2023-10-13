package ir.mahsan.challenge.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    @SerializedName("status") val status: String,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
) : Parcelable

@Parcelize
data class BaseResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: ArrayList<Article>,
) : Parcelable

