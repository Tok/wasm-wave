package data

import util.MathUtil
import data.Pos

data class Particle(val name: String, var pos: Pos) {
    fun x() = pos.x
    fun y() = pos.y

    fun move(rand1: Double, rand2: Double) = with(pos) {
        pos = Pos(
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
        )
    }

    fun moveTo(newX: Int, newY: Int) {
        pos = Pos(newX, newY)
    }

    override fun toString(): String = name + ": " + pos.toString()

    companion object {
        fun create(name: String) = Particle(name, Pos.default)
    }
}
