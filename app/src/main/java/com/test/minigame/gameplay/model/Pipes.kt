package com.test.minigame.gameplay.model

import kotlin.math.floor

class Pipes(
    screenHeight: Double,
    screenWidth: Double
) {
    private var dist = (screenHeight / 1.1)
    private var bottomY = floor(Math.random() * (screenHeight / 2) + .3 * screenHeight)
    private var topY = bottomY - dist
    private var x = screenWidth
    private var height = screenHeight
    private var width = screenWidth
    private var dx = .014 * screenWidth

    fun moveX() {
        x -= dx
    }

    fun reset() {
        x = width
        bottomY = floor(Math.random() * (height / 2) + .3 * height)
        topY = bottomY - dist
    }
}