package com.test.minigame.gameplay.database

import androidx.annotation.WorkerThread
import com.test.minigame.gameplay.model.ScoreModel
import kotlinx.coroutines.flow.Flow

class ScoreRepository(private val scoreDao: ScoreDao) {

    val allWords: Flow<List<ScoreModel>> = scoreDao.getScores()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(score: ScoreModel) {
        scoreDao.insert(score)
    }
}