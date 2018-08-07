package util.inaccurate

import kotlinx.interop.wasm.math.Math

object ShittyMath {
    val sqrtIterations = 10 //tune this...

    //http://mathonweb.com/help_ebook/html/algorithms.htm#sqrt
    fun sqrt(x: Double): Double {
        tailrec fun sqrt0(i: Int, guess: Double): Double {
            return if (i >= sqrtIterations) guess
            else sqrt0(i + 1, (guess / 2.0) + (x / (2.0 * guess)))
        }
        return sqrt0(0, x / 2.0)
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
