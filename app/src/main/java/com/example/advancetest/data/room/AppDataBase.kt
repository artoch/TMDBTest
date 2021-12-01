package com.example.advancetest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.advancetest.data.room.dao.MovieDao
import com.example.advancetest.data.room.entities.MovieItemRoom

@Database(entities = [MovieItemRoom::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun  getAppDao(): MovieDao
}