package ir.jetpack.challenge.component.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.jetpack.challenge.BuildConfig
import ir.jetpack.challenge.model.datasource.NewsApiDatasource
import ir.jetpack.challenge.model.repository.NewsRepository
import ir.jetpack.challenge.model.repository.NewsRepositoryImpl
import ir.jetpack.challenge.util.getOkHttpClient
import ir.jetpack.challenge.util.getRetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = getOkHttpClient()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("BASE_URL") baseUrl: String,
    ): Retrofit = getRetrofitClient(baseUrl, okHttpClient)

    @Provides
    @Singleton
    fun getUserWebApiDatasource(retrofit: Retrofit): NewsApiDatasource =
        retrofit.create(NewsApiDatasource::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(datasource: NewsApiDatasource): NewsRepository =
        NewsRepositoryImpl(datasource)

}