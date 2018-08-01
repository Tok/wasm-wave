package data

import kotlin.math.abs
import kotlin.math.sqrt

data class Pos(val x: Int, val y: Int) {
    fun xx() = x.toDouble()
    fun yy() = y.toDouble()
    fun xDiff(other: Pos) = x - other.x
    fun yDiff(other: Pos) = y - other.y
    fun distanceTo(other: Pos): Double {
        val xPow = xDiff(other) * xDiff(other)
        val yPow = yDiff(other) * yDiff(other)
        return abs(sqrt(xPow.toDouble() + yPow.toDouble()))
    }
    override fun toString() = "X$x:Y$y"
}
