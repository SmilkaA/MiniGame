package com.test.minigame.gameplay.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
class ScoreModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "points") val points: Int,
    @ColumnInfo(name = "time_played") val timePlayed: String
)