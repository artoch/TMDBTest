package com.example.advancetest.di.data.service

import com.example.advancetest.data.network.api.ApiService
import com.example.advancetest.data.network.service.MovieService
import com.example.advancetest.data.network.service.MovieServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object ServiceModule {

    @Singleton
    @Provides
    fun provideServiceApp(
        service: ApiService
    ): MovieService = MovieServiceImpl(
        service = service
    )

}