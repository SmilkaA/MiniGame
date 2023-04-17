package com.test.minigame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.myapplication.R

class StartGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start_screen)
    }
}