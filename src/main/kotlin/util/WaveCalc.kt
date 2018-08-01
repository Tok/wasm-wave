package util

import config.Constants
import config.Wave
import data.Complex
import data.Pos
import kotlinx.interop.wasm.math.*
import util.MathUtil

/**
 * Recreated by Zir on 30.07.2018.
 * Transpiled and rearranged from: https://github.com/Tok/Erwin/tree/master/src/main/java/erwin
 * In: https://github.com/Tok/Zir-Watchface/blob/master/Wearable/src/main/kotlin/zir/teq/wearable/watchface/util/WaveCalc.kt
 */
object WaveCalc {
    fun calc(from: Pos, to: Pos, t: Double, frequency: Double, mass: Double) =
            calc(from.xx(), from.yy(), to.xx(), to.yy(), t, frequency, mass)

    private fun calc(xFrom: Double, yFrom: Double, xTo: Double, yTo: Double,
             t: Double, frequency: Double, mass: Double): Complex {
        val distance = calcDistance(xFrom, yFrom, xTo, yTo)
        val phase = (t + (frequency * distance / mass)) % Constants.TAU
        val mag = calcIntensity(distance, xFrom, yFrom)
        return Complex.valueOf(mag, phase)
    }

    private fun addSquares(first: Double, second: Double): Double =
            (first * first) + (second * second)

    private fun calcIntensity(distance: Double, x: Double, y: Double): Double {
        val dividend = Wave.INTENSITY * (x + y)
        val divisor = (MathUtil.square(distance) * Constants.TAU)
        return minOf(1.0, dividend / divisor).toDouble()
    }

    private fun calcDistance(xFrom: Double, yFrom: Double, xTo: Double, yTo: Double): Double {
        return Math.sqrt(addSquares(xFrom - xTo, yFrom - yTo).toDouble()).toDouble()
    }
}
