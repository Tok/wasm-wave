package util.inaccurate

import config.Constants
import util.MathUtil

object ShittyTrig {
    private tailrec fun fixPeriodicity(x: Double): Double = when {
        x < 0.0 -> fixPeriodicity(x + Constants.tau)
        x >= Constants.tau -> fixPeriodicity(x - Constants.tau)
        else -> x
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#sin
    fun sin(z: Double): Double {
        val x = fixPeriodicity(z)
        if (x > Constants.pi) return -sin(x - Constants.pi)
        if (x > Constants.half_pi) return sin(Constants.half_pi - (x - Constants.half_pi))
        if (x > Constants.quarter_pi) return cos(Constants.half_pi - x)
        return x -
                (x * x * x / 6.0) +
                (x * x * x * x * x / 120.0)
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#cos
    fun cos(z: Double): Double {
        val x = fixPeriodicity(z)
        if (x > Constants.pi) return -cos(x - Constants.pi)
        if (x > Constants.half_pi) return -cos(Constants.half_pi - (x - Constants.half_pi))
        if (x > Constants.quarter_pi) return sin(Constants.half_pi - x)
        return 1 -
                (x * x / 2.0) +
                (x * x * x * x / 24.0) -
                (x * x * x * x * x * x / 720.0)
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#arctan
    private fun atan(x: Double): Double {
        if (x < 0.0) return -atan(-x)
        if (x > 1.0) return Constants.half_pi - atan(1.0 / x)
        if (x > (2.0 - Constants.sqrt_three))
            return Constants.sixth_pi +
                    atan(((Constants.sqrt_three * x) - 1) /
                            (Constants.sqrt_three + x))
        return x - (x * x * x / 3.0) + (x * x * x * x * x / 5.0)
    }

    //http://mathonweb.com/help_ebook/html/algorithms.htm#tan
    private fun tan(x: Double): Double {
        if (x < 0.0) return tan(x + Constants.pi)
        if (x > Constants.pi) return tan(x - Constants.pi)
        if (x > Constants.half_pi) return -tan(Constants.pi - x)
        if (x > Constants.quarter_pi) return 1.0 / tan(Constants.half_pi - x)
        if (x > Constants.eighth_pi) {
            val halfTan = tan(x * 0.5)
            return (2.0 * halfTan) / (1 - (halfTan * halfTan))
        }
        return x +
                (x * x * x / 3) +
                (2.0 * x * x * x * x * x / 15) +
                (17.0 * x * x * x * x * x * x * x / 315)
    }

    //https://www.medcalc.org/manual/atan2_function.php
    fun atan2(y: Double, x: Double): Double = atan(y / x) + when {
        x > 0.0 -> 0.0
        x < 0.0 && y >= 0.0 -> Constants.pi
        x < 0.0 && y < 0.0 -> -Constants.pi
        x == 0.0 && y > 0.0 -> +Constants.tau
        x == 0.0 && y < 0.0 -> -Constants.tau
        else -> 0.0 //undefined
    }
}
