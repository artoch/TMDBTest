package com.example.advancetest.di.data.repository

import com.example.advancetest.data.network.repository.MovieRepository
import com.example.advancetest.data.network.repository.MovieRepositoryImpl
import com.example.advancetest.data.network.service.MovieService
import com.example.advancetest.data.room.use_case.UCRAddMovies
import com.example.advancetest.data.room.use_case.UCRGetMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object RepositoryRemoteModule {

    @Singleton
    @Provides
    fun provideRepositoryRemote(
        service: MovieService,
        ucrAddMovies: UCRAddMovies,
        ucrGetMovies: UCRGetMovies
    ):MovieRepository = MovieRepositoryImpl(
        service = service,
        ucrAddMovies = ucrAddMovies,
        ucrGetMovies = ucrGetMovies
    )
    
}