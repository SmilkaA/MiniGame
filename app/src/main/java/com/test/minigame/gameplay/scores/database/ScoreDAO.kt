package com.test.minigame.gameplay.scores.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.minigame.gameplay.scores.model.ScoreModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Query("SELECT * FROM score_table ORDER BY points ASC LIMIT 5")
    fun getScores(): Flow<List<ScoreModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: ScoreModel)
}