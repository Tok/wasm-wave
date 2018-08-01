package data

import util.MathUtil

data class Pos(val x: Int, val y: Int) {
    fun xx() = x.toDouble()
    fun yy() = y.toDouble()
    fun xDiff(other: Pos) = xx() - other.xx()
    fun yDiff(other: Pos) = yy() - other.yy()
    fun distanceTo(other: Pos): Double {
        val xPow = xDiff(other) * xDiff(other)
        val yPow = yDiff(other) * yDiff(other)
        return MathUtil.abs(MathUtil.sqrt(xPow + yPow))
    }
    override fun toString() = "X$x:Y$y"
}
