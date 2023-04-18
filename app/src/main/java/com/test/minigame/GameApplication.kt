package com.test.minigame

import android.app.Application
import com.test.minigame.gameplay.database.ScoreDatabase
import com.test.minigame.gameplay.database.ScoreRepository

class GameApplication : Application() {
    val database by lazy { ScoreDatabase.getDatabase(this) }
    val repository by lazy { ScoreRepository(database.scoreDao()) }
}