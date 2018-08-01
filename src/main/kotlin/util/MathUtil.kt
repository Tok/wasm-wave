package util

import kotlinx.interop.wasm.math.Math

object MathUtil {
    val preferInterop = false

    fun abs(n: Double): Double = if (n >= 0.0) n else -n
    fun min(first: Double, second: Double): Double =
            if (first < second) first else second

    fun max(first: Double, second: Double): Double =
            if (first > second) first else second

    fun atan2(y: Double, x: Double): Double = Math.atan2(y, x)
    fun cos(n: Double): Double = Math.cos(n)
    fun sin(n: Double): Double = Math.sin(n)

    fun sqrt(n: Double): Double =
            if (preferInterop) Math.sqrt(n) else shittySqrt(n)

    fun pow(y: Double, x: Double): Double = Math.pow(y, x)

    tailrec fun fact(n: Double): Double =
            if (n <= 1.0) 1.0 else n * fact(n - 1.0)

    private fun shittySqrt(n: Double): Double {
        val sqrtIterations = 20 //TODO tune this...
        tailrec fun refine(i: Int, min: Double, max: Double): Double {
            val avg = (min + max) / 2.0
            if (i >= sqrtIterations) return avg
            else {
                val pow = avg * avg
                return when {
                    pow > n -> refine(i + 1, min, avg)
                    pow < n -> refine(i + 1, avg, max)
                    else -> avg
                }
            }
        }
        return refine(0, 0.0, n)
    }
}
