package ir.mahsan.challenge.model.datasource

import ir.mahsan.challenge.model.dto.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiDatasource {

    @GET("everything")
    suspend fun getList(@Query("q") q: String = "world",@Query("page") page: Int, @Query("pageSize") pageSize: Int): BaseResponse

}