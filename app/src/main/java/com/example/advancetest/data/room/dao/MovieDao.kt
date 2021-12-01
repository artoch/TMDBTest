package com.example.advancetest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.room.entities.MovieItemRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table WHERE id IN (SELECT id FROM movie_table ORDER BY RANDOM() LIMIT :size) ")
    suspend fun getMovies(size: Int): List<MovieItemRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movie: List<MovieItemRoom>)
}