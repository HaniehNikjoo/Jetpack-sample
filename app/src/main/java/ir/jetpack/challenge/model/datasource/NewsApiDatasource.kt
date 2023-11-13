package ir.jetpack.challenge.model.datasource

import ir.jetpack.challenge.model.dto.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiDatasource {

    @GET("everything")
    suspend fun getList(@Query("q") q: String = "TechCrunch", @Query("page") page: Int, @Query("pageSize") pageSize: Int): BaseResponse

}