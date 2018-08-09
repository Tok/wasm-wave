package util

import config.Constants
import config.Wave
import data.Complex
import data.Pos
import util.MathUtil

/**
 * Recreated by Zir on 30.07.2018.
 * Transpiled and rearranged from: https://github.com/Tok/Erwin/tree/master/src/main/java/erwin
 * In: https://github.com/Tok/Zir-Watchface/blob/master/Wearable/src/main/kotlin/zir/teq/wearable/watchface/util/WaveCalc.kt
 */
object WaveCalc {
    fun calc(from: Pos, to: Pos, t: Double, freq: Double, globInten: Double) =
            calc(from.xx(), from.yy(), to.xx(), to.yy(), t, freq, globInten)
    private fun calc(xFrom: Double, yFrom: Double,
                     xTo: Double, yTo: Double,
                     t: Double, freq: Double, globInten: Double): Complex {
        val distance = calcDistance(xFrom, yFrom, xTo, yTo)
        val standing = (freq * distance / Wave.mass)
        val phase: Double = (t + standing) % Constants.tau
        val magnitude: Double = calcIntensity(distance, xFrom, yFrom, globInten)
        return Complex.valueOf(magnitude, phase)
    }

    fun calcPair(from: Pos, to: Pos, t: Double, freq: Double, globInten: Double) =
            calcPair(from.xx(), from.yy(), to.xx(), to.yy(), t, freq, globInten)
    private fun calcPair(xFrom: Double, yFrom: Double,
                     xTo: Double, yTo: Double,
                     t: Double, freq: Double, globInten: Double):
            Pair<Double, Double> {
        val distance = calcDistance(xFrom, yFrom, xTo, yTo)
        val standing = (freq * distance / Wave.mass)
        val phase: Double = (t + standing) % Constants.tau
        val magnitude: Double = calcIntensity(distance, xFrom, yFrom, globInten)
        return Pair(magnitude, phase)
    }

    private fun addSquares(first: Double, second: Double): Double =
            (first * first) + (second * second)

    private fun calcIntensity(distance: Double, x: Double, y: Double,
                              globalIntensity: Double): Double {
        val dividend = globalIntensity * (x + y)
        val divisor = distance * distance * Constants.tau
        return minOf(1.0, dividend / divisor).toDouble()
    }

    private fun calcDistance(xFrom: Double, yFrom: Double, xTo: Double, yTo: Double): Double {
        return MathUtil.sqrt(addSquares(xFrom - xTo, yFrom - yTo))
    }
}
