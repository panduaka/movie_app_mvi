package com.example.movieapp.data.remote_service

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RetrofitInstance @Inject constructor(private val okHttpClient: OkHttpClient, private val moshi: Moshi) {
    private val BASE_URL = "https://api.themoviedb.org/3/"
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}