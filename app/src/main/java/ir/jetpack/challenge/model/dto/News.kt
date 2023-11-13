package ir.jetpack.challenge.model.dto

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ArticleSource(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("category") val category: String,
    @SerializedName("language") val language: String,
    @SerializedName("country") val country: String,
): Parcelable
@Keep
@Parcelize
data class Article(
    @SerializedName("source") val source: ArticleSource,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String,
) : Parcelable
