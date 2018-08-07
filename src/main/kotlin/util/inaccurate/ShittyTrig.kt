package util.inaccurate

import config.Constants
import util.MathUtil

/* Trigonometry functions mostly transcoded from
 * https://stackoverflow.com/a/22824049
 */
object ShittyTrig {
    val trigIterations = 20 //tune this...

    fun sin(x: Double): Double {
        tailrec fun sin0(i: Int, y: Double, s: Double): Double {
            if (i >= trigIterations) return y
            else {
                val dividend = s * ShittyMath.pow(x, i.toDouble())
                val divisor = ShittyMath.fact(i.toDouble())
                val nextY = y + (dividend / divisor)
                return sin0(i + 2, nextY, s * -1.0)
            }
        }
        return sin0(3, x, -1.0)
    }

    fun cos(x: Double): Double {
        tailrec fun cos0(i: Int, y: Double, s: Double): Double {
            if (i >= trigIterations) return y
            else {
                val dividend = s * ShittyMath.pow(x, i.toDouble())
                val divisor = ShittyMath.fact(i.toDouble())
                val nextY = y + (dividend / divisor)
                return cos0(i + 2, nextY, s * -1.0)
            }
        }
        return cos0(2, 1.0, -1.0)
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#arctan
    private fun approxAtan(x: Double): Double {
        if (x < 0.0) return -approxAtan(-x)
        if (x > 1.0) return Constants.half_pi - approxAtan(1.0 / x)
        if (x > (2.0 - Constants.sqrt_three))
            return Constants.sixth_pi +
                    approxAtan(((Constants.sqrt_three * x) - 1) /
                            (Constants.sqrt_three + x))
        return x - (x * x * x / 3.0) + (x * x * x * x * x / 5.0)
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#tan
    private fun approxTan(x: Double): Double {
        if (x < 0.0) return approxTan(x + Constants.pi)
        if (x > Constants.pi) return approxTan(x - Constants.pi)
        if (x > Constants.half_pi) return -approxTan(Constants.pi - x)
        if (x > Constants.quarter_pi) return 1.0 / approxTan(Constants.half_pi - x)
        if (x > Constants.eighth_pi) {
            val halfTan = approxTan(x * 0.5)
            return (2.0 * halfTan) / (1 - (halfTan * halfTan))
        }
        return x +
                (x * x * x / 3) +
                (2.0 * x * x * x * x * x / 15) +
                (17.0 * x * x * x * x * x * x * x / 315)
    }

    //https://www.medcalc.org/manual/atan2_function.php
    fun atan2(y: Double, x: Double): Double = approxAtan(y / x) + when {
        x > 0.0 -> 0.0
        x < 0.0 && y >= 0.0 -> Constants.pi
        x < 0.0 && y < 0.0 -> -Constants.pi
        x == 0.0 && y > 0.0 -> +Constants.tau
        x == 0.0 && y < 0.0 -> -Constants.tau
        x == 0.0 && y == 0.0 -> 0.0 //undefined
        else -> 0.0
    }
}
