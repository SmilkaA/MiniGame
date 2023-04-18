package com.test.minigame.gameplay.model

class Bird {
    var x: Float = 50F
    var y: Float = 500F
    var isAlive: Boolean = true

    fun updatePosition(dy: Int) {
        y += dy
    }
}