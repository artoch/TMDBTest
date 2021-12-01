package com.example.advancetest.di.data.repository

import com.example.advancetest.data.room.dao.MovieDao
import com.example.advancetest.data.room.repo.MovieRoomRepo
import com.example.advancetest.data.room.repo.MovieRoomRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object RepositoryRoomModule {

    @Singleton
    @Provides
    fun provideRepositoryRemote(
        dao: MovieDao
    ): MovieRoomRepo = MovieRoomRepoImpl(
        dao
    )

}