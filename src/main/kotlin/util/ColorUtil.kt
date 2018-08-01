package util

import config.Constants
import data.Complex
import kotlinx.interop.wasm.math.Math

/*
 * Recycled from https://github.com/Tok/Zir-Watchface/blob/master/Wearable/src/main/kotlin/zir/teq/wearable/watchface/util/ColorUtil.kt
 * In: https://github.com/Tok/Q-gress/blob/master/src/util/ColorUtil.kt
 */
object ColorUtil {
    val MAX_RGB = 0xFF
    val BLACK = "#000000"
    val WHITE = "#ffffff"

    private fun normalizePhase(phase: Double) = when {
        phase < 0.0 -> phase + Constants.TAU
        phase > Constants.TAU -> phase - Constants.TAU
        else -> phase
    }

    fun getColor(c: Complex): String = getColorFromMagnitudeAndPhase(c.magnitude.toDouble(), c.phase)
    private fun getColorFromMagnitudeAndPhase(magnitude: Double, phase: Double): String {
        val p = normalizePhase(phase) * 6.0 / Constants.TAU
        val range = Math.min(5.0, Math.max(0.0, p)).toInt()
        val fraction = p - range
        val rgbValues = fullSpectrum(range, fraction)
        val mag = magnitude * MAX_RGB.toInt()
        val red = (rgbValues.first * mag).toInt()
        val green = (rgbValues.second * mag).toInt()
        val blue = (rgbValues.third * mag).toInt()
        val color = "#" + col(red) + col(green) + col(blue)
        return color
    }

    private fun col(i: Int): String {
        val str = i.toString(16)
        return if (str.length == 1) "0" + str else str
    }

    private fun fullSpectrum(range: Int, fraction: Double) = when (range) {
        0 -> Triple(1.0, fraction, 0.0) //Red -> Yellow
        1 -> Triple(1.0 - fraction, 1.0, 0.0) //Yellow -> Green
        2 -> Triple(0.0, 1.0, fraction) //Green -> Cyan
        3 -> Triple(0.0, 1.0 - fraction, 1.0) //Cyan -> Blue
        4 -> Triple(fraction, 0.0, 1.0) //Blue -> Magenta
        5 -> Triple(1.0, 0.0, 1.0 - fraction) //Magenta -> Red
        else -> throw IllegalArgumentException("Out of range: " + range)
    }
}
