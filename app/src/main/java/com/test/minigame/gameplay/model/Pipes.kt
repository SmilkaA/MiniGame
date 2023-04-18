package com.test.minigame.gameplay.model

import kotlin.math.floor

class Pipes(
    screenHeight: Int,
    screenWidth: Int
) {
    var dist = (screenHeight / 1.1)
    var bottomY: Float = floor(Math.random() * (screenHeight / 2) + .3 * screenHeight).toFloat()
    var topY: Float = (bottomY - dist).toFloat()
    var x: Float = screenWidth * 1.0F
    var height = screenHeight
    var width = screenWidth
    var dx: Float = .014F * screenWidth

    fun moveX() {
        x -= dx
    }

    fun reset() {
        x = width * 1.0F
        bottomY = floor(Math.random() * (height / 2) + .3 * height).toFloat()
        topY = (bottomY - dist).toFloat()
    }
}