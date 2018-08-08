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
    fun move(rand1: Double, rand2: Double) = Pos(
            if (rand1 < 0.5) x + 1 else x - 1,
            if (rand2 < 0.5) y + 1 else y - 1
    )
    override fun toString(): String {
        fun pad(t: Int) = t.toString().padStart(3, '0')
        return pad(x) + ":" + pad(y)
    }

    companion object {
        val default = Pos(0, 0)
    }
}
