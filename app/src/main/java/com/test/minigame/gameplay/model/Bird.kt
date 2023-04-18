package com.test.minigame.gameplay.model

class Bird {
    private var x: Int = 50
    private var y: Int = 500
    private var isAlive: Boolean = true
    var millis = 0

    fun updatePosition(dy: Int) {
        y += dy
    }
}