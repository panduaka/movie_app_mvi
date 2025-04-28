package com.example.movieapp.di


import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.data.remote_service.MovieApiService
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
//}

@Module
@InstallIn(SingletonComponent::class)
object  RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieApiService: MovieApiService): MovieRepository {
        return MovieRepositoryImpl(movieApiService)
    }
}