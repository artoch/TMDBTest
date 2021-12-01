package com.example.advancetest.di.data.use_case

import com.example.advancetest.data.room.repo.MovieRoomRepo
import com.example.advancetest.data.room.use_case.UCRAddMovies
import com.example.advancetest.data.room.use_case.UCRGetMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object UCRoomModule {

    @Singleton
    @Provides
    fun provideUCRMovieList(
        repository: MovieRoomRepo
    ): UCRGetMovies = UCRGetMovies(repository)

    @Singleton
    @Provides
    fun provideUCRMovieCreate(
        repository: MovieRoomRepo
    ): UCRAddMovies = UCRAddMovies(repository)
}