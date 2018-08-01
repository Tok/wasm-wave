package util.inaccurate

import kotlinx.interop.wasm.math.Math

object ShittyMath {
    val sqrtIterations = 20 //tune this...

    fun sqrt(n: Double): Double {
        tailrec fun sqrt0(i: Int, min: Double, max: Double): Double {
            val avg = (min + max) / 2.0
            if (i >= sqrtIterations) return avg
            else {
                val pow = avg * avg
                return when {
                    pow > n -> sqrt0(i + 1, min, avg)
                    pow < n -> sqrt0(i + 1, avg, max)
                    else -> avg
                }
            }
        }
        return sqrt0(0, 0.0, n)
    }

    fun pow(a: Double, b: Double): Double {
        tailrec fun pow0(i: Int, c: Double): Double =
                if (i >= b) c
                else pow0(i + 1, c * a)
        return pow0(0, 1.0)
    }

    fun fact(n: Double): Double {
        tailrec fun shittyFact0(i: Int, ret: Double): Double =
                if (i > n) ret
                else shittyFact0(i + 1, ret * i.toDouble())
        return shittyFact0(1, 1.0)
    }
}
