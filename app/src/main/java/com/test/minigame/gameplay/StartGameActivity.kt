package com.test.minigame.gameplay

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.test.minigame.gameplay.fragments.PlayingFragment
import com.test.minigame.gameplay.fragments.ScoresFragment
import com.test.myapplication.R


class StartGameActivity : AppCompatActivity() {
    private val remoteConfig = Firebase.remoteConfig
    private lateinit var webView: WebView
    private lateinit var gameLogo: ImageView
    private lateinit var playButton: Button
    private lateinit var scoresButton: Button
    private var statusParam: Boolean = false
    private lateinit var linkParam: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start_screen)
        initComponents()
    }

    private fun initComponents() {
        initRemoteConfig()
        webView = findViewById(R.id.webview)
        gameLogo = findViewById(R.id.game_logo)
        playButton = findViewById(R.id.play_button)
        scoresButton = findViewById(R.id.scores_button)

        playButton.setOnClickListener {
            if (!statusParam) {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(PlayingFragment::class.java.name)
                    .add(R.id.main_container, PlayingFragment()).commit()
            } else {
                gameLogo.visibility = View.GONE
                playButton.visibility = View.GONE
                scoresButton.visibility = View.GONE
                webView.webViewClient = WebViewClient()
                webView.visibility = View.VISIBLE
                webView.loadUrl(linkParam)
            }
        }

        scoresButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .addToBackStack(StartGameActivity::class.java.name)
                .add(R.id.main_container, ScoresFragment()).commit()
        }
    }

    private fun initRemoteConfig() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    statusParam =
                        remoteConfig.getBoolean(getString(R.string.remote_config_status_param))
                    linkParam = remoteConfig.getString(getString(R.string.remote_config_link_param))
                }
            }
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                remoteConfig.activate().addOnCompleteListener {
                    statusParam = remoteConfig.getBoolean(getString(R.string.remote_config_status_param))
                    linkParam = remoteConfig.getString(getString(R.string.remote_config_link_param))
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                error.printStackTrace()
            }
        })
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            if (webView.visibility == View.VISIBLE) {
                webView.visibility = View.GONE
                gameLogo.visibility = View.VISIBLE
                playButton.visibility = View.VISIBLE
                scoresButton.visibility = View.VISIBLE
            } else {
                supportFragmentManager.popBackStack()
            }
        }
    }
}