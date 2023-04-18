package com.test.minigame.gameplay

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.test.minigame.gameplay.fragments.PlayingFragment
import com.test.minigame.gameplay.fragments.ScoresFragment
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
            supportFragmentManager.beginTransaction()
                .addToBackStack(PlayingFragment::class.java.name)
                .add(R.id.main_container, PlayingFragment()).commit()
        }

        val scoresButton: Button = findViewById(R.id.scores_button)
        scoresButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .addToBackStack(StartGameActivity::class.java.name)
                .add(R.id.main_container, ScoresFragment()).commit()
        }
    }
}