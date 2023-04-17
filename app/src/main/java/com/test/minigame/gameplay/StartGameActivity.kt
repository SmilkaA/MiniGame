package com.test.minigame.gameplay

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.test.minigame.gameplay.scores.ScoresFragment
import com.test.myapplication.R


class StartGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start_screen)
        initComponents()
    }

    private fun initComponents() {
        val playButton: Button = findViewById(R.id.play_button)
        playButton.setOnClickListener {

        }

        val scoresButton: Button = findViewById(R.id.scores_button)
        scoresButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .addToBackStack(StartGameActivity::class.java.name)
                .add(R.id.main_container, ScoresFragment()).commit()
        }
    }
}