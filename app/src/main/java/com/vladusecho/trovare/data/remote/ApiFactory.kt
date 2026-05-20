package com.vladusecho.trovare.data.remote

import com.vladusecho.trovare.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://api.poiskkino.dev/"
    private const val API_KEY = BuildConfig.API_KEY

    private val okClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(createApiKeyInterceptor())
        .build()

    private fun createApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestWithApi = originalRequest.newBuilder()
                .header("X-Api-Key", API_KEY)
                .build()
            chain.proceed(requestWithApi)
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}