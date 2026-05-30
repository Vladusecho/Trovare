package com.vladusecho.trovare.di

import com.vladusecho.trovare.data.remote.ApiFactory
import com.vladusecho.trovare.data.remote.ApiService
import com.vladusecho.trovare.data.repository.MovieRepositoryImpl
import com.vladusecho.trovare.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {

        @Provides
        @Singleton
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}