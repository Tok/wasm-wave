package data

import util.MathUtil
import data.Pos

data class Particle(val name: String, val pos: Pos) {
    val x = pos.x
    val y = pos.y
    fun move(rand1: Double, rand2: Double) = Particle(name, Pos(
            when {
                rand1 < 0.1 -> x + 1
                rand1 < 0.2 -> x - 1
                else -> x
            },
            when {
                rand2 < 0.1 -> y + 1
                rand2 < 0.2 -> y - 1
                else -> y
            }
    ))
    fun moveTo(x: Int, y: Int) = Particle(name, Pos(x, y))

    override fun toString(): String = name + ": " + pos.toString()

    companion object {
        fun create(name: String) = Particle(name, Pos.default)
    }
}
