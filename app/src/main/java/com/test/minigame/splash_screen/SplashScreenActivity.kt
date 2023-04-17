package com.test.minigame.splash_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.test.minigame.StartGameActivity
import com.test.myapplication.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val backgroundImage: ImageView = findViewById(R.id.splashscreen_icon_view)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_anim)
        backgroundImage.startAnimation(slideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, StartGameActivity::class.java))
            finish()
        }, SPLASH_SCREEN_DISPLAYED_TIME)
    }

    companion object {
        const val SPLASH_SCREEN_DISPLAYED_TIME = 2000L
    }
}
