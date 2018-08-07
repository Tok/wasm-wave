package util.inaccurate

import config.Constants.tau
import config.Constants.pi
import config.Constants.half_pi
import config.Constants.quarter_pi
import config.Constants.sixth_pi
import config.Constants.eighth_pi
import config.Constants.sqrt_three
import util.MathUtil

object ShittyTrig {
    private tailrec fun fixPeriodicity(x: Double, range: Double): Double = when {
        x < 0.0 -> fixPeriodicity(x + range, range)
        x >= range -> fixPeriodicity(x - range, range)
        else -> x
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#sin
    fun sin(z: Double): Double {
        val x = fixPeriodicity(z, tau)
        return when {
            x > pi -> -sin(x - pi)
            x > half_pi -> sin(half_pi - (x - half_pi))
            x > quarter_pi -> cos(half_pi - x)
            else -> x -
                    (x * x * x / 6.0) +
                    (x * x * x * x * x / 120.0)
        }
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#cos
    fun cos(z: Double): Double {
        val x = fixPeriodicity(z, tau)
        return when {
            x > pi -> -cos(x - pi)
            x > half_pi -> -cos(half_pi - (x - half_pi))
            x > quarter_pi -> sin(half_pi - x)
            else -> 1 -
                    (x * x / 2.0) +
                    (x * x * x * x / 24.0) -
                    (x * x * x * x * x * x / 720.0)
        }
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#arctan
    private fun atan(x: Double): Double {
        if (x < 0.0) return -atan(-x)
        if (x > 1.0) return half_pi - atan(1.0 / x)
        if (x > (2.0 - sqrt_three))
            return sixth_pi +
                    atan(((sqrt_three * x) - 1) /
                            (sqrt_three + x))
        return x - (x * x * x / 3.0) + (x * x * x * x * x / 5.0)
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#tan
    private fun tan(z: Double): Double {
        val x = fixPeriodicity(z, pi)
        return when {
            x > half_pi -> -tan(pi - x)
            x > quarter_pi -> 1.0 / tan(half_pi - x)
            x > eighth_pi -> {
                val halfTan = tan(x * 0.5)
                (2.0 * halfTan) / (1 - (halfTan * halfTan))
            }
            else -> x +
                    (x * x * x / 3) +
                    (2.0 * x * x * x * x * x / 15) +
                    (17.0 * x * x * x * x * x * x * x / 315)
        }
    }

    //https://www.medcalc.org/manual/atan2_function.php
    fun atan2(y: Double, x: Double): Double = atan(y / x) + when {
        x > 0.0 -> 0.0
        x < 0.0 && y >= 0.0 -> pi
        x < 0.0 && y < 0.0 -> -pi
        x == 0.0 && y > 0.0 -> +tau
        x == 0.0 && y < 0.0 -> -tau
        else -> 0.0 //undefined
    }
}
