package com.example.movieapp.data.remote_service

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val AUTHORIZATION_HEADER = "Authorization"
    private const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YzA3M2ViNjc1ZGNlZTY0NDYxMjQ1M2Q3ZWEwNzM2NiIsIm5iZiI6MTc0MjQxOTkxNS4xMzMsInN1YiI6IjY3ZGIzN2NiOWYzNThmNGExMzdmODU0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.332uWrq_Gil3_6882D-UJwe2RUwKJ5PU9BiNgw6DcL8"

    fun createMovieService(): MovieApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(createOkHttpClient())
            .build()
            .create(MovieApiService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log request and response details
        }

        val authInterceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader(AUTHORIZATION_HEADER, "Bearer $API_KEY")
                .build()
            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}