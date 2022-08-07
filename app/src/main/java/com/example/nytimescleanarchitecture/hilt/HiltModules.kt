package com.example.nytimescleanarchitecture.hilt

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.chuckerteam.chucker.api.ChuckerCollector
import com.example.nytimescleanarchitecture.data.network.RetrofitImpl
import com.example.nytimescleanarchitecture.data.remote.ApiService
import com.example.nytimescleanarchitecture.data.repository.PopularArticleRepositoryImpl
import com.example.nytimescleanarchitecture.domain.repository.PopularArticleRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun getResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun getChuckerCollector(@ApplicationContext context: Context): ChuckerCollector =
        ChuckerCollector(
            context = context,
            showNotification = true
        )

    @Provides
    @Singleton
    fun getRetrofit(retrofitImpl: RetrofitImpl): Retrofit = retrofitImpl.retrofit

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun getPopularArticlesRepository(apiService: ApiService): PopularArticleRepository {
        return PopularArticleRepositoryImpl(apiService)
    }
}