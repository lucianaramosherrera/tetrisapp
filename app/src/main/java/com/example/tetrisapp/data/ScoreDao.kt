package com.example.tetrisapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tetrisapp.model.Score

@Dao
interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: Score)

    @Query("SELECT * FROM scores ORDER BY puntos DESC, fecha DESC")
    fun getAllScores(): LiveData<List<Score>>

    @Query("DELETE FROM scores")
    suspend fun deleteAll()

    @Query("SELECT * FROM scores ORDER BY puntos DESC, fecha DESC")
    suspend fun getAllScoresNow(): List<Score>
}
