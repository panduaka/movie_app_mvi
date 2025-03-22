package com.example.movieapp.di

import com.example.movieapp.data.remote_service.MovieApiService
import com.example.movieapp.data.remote_service.RetrofitInstance
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val AUTHORIZATION_HEADER = "Authorization"
    private const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YzA3M2ViNjc1ZGNlZTY0NDYxMjQ1M2Q3ZWEwNzM2NiIsIm5iZiI6MTc0MjQxOTkxNS4xMzMsInN1YiI6IjY3ZGIzN2NiOWYzNThmNGExMzdmODU0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.332uWrq_Gil3_6882D-UJwe2RUwKJ5PU9BiNgw6DcL8" // Replace with your actual API key

    @Provides
    @Singleton
    fun provideMovieService(retrofitInstance: RetrofitInstance): MovieApiService {
        return retrofitInstance.createRetrofit().create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient, moshi: Moshi): RetrofitInstance {
        return RetrofitInstance(okHttpClient, moshi)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader(AUTHORIZATION_HEADER, "Bearer $API_KEY")
                .build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log request and response details
        }
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}