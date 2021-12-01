package com.example.advancetest.di

import android.content.Context
import androidx.room.Room
import com.example.advancetest.common.const.DATABASE_NAME
import com.example.advancetest.data.room.AppDataBase
import com.example.advancetest.data.room.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object RoomModule {
    @Singleton
    @Provides
    fun appDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun postDao(appDatabase: AppDataBase): MovieDao {
        return appDatabase.getAppDao()
    }
}