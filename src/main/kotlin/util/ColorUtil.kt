package util

import config.Constants
import data.Complex
import data.Spectrum
import util.MathUtil

private typealias RGB = Triple<Double, Double, Double>

/*
 * Recycled from https://github.com/Tok/Zir-Watchface/blob/master/Wearable/src/main/kotlin/zir/teq/wearable/watchface/util/ColorUtil.kt
 * In: https://github.com/Tok/Q-gress/blob/master/src/util/ColorUtil.kt
 */
object ColorUtil {
    val MAX_RGB = 0xFF
    val BLACK = "#000000"
    val WHITE = "#ffffff"
    val RED = "#ff0000"
    val GREEN = "#00ff00"
    val BLUE = "#0000ff"

    private fun normalizePhase(phase: Double) = when {
        phase < 0.0 -> phase + Constants.tau
        phase > Constants.tau -> phase - Constants.tau
        else -> phase
    }

    fun getColor(spec: Spectrum, c: Complex): String =
            getColorFromMagAndPhase(spec, c.magnitude.toDouble(), c.phase)

    private fun getColorFromMagAndPhase(
            spec: Spectrum, magnitude: Double, phase: Double): String {
        val p = normalizePhase(phase) * 6.0 / Constants.tau
        val range = MathUtil.min(5.0, MathUtil.max(0.0, p)).toInt()
        val fraction = p - range
        val rgbValues = calcTriple(spec, range, fraction)
        val mag = magnitude * MAX_RGB.toInt()
        val red = (rgbValues.first * mag).toInt()
        val green = (rgbValues.second * mag).toInt()
        val blue = (rgbValues.third * mag).toInt()
        val color = "#" + col(red) + col(green) + col(blue)
        return color
    }

    private fun col(i: Int): String {
        fun min(first: Int, second: Int): Int =
                if (first < second) first else second

        fun max(first: Int, second: Int): Int =
                if (first > second) first else second

        val clip = min(MAX_RGB, max(0, i))
        val str = clip.toString(16)
        return if (str.length == 1) "0" + str else str
    }

    private fun calcTriple(spec: Spectrum, range: Int, frac: Double): RGB = when {
        spec == Spectrum.FULL -> fullSpectrum(range, frac)
        else -> whiteLines(range, frac)
    }

    private fun whiteLines(range: Int, frac: Double): RGB = when (range) {
        0, 2, 4 -> RGB(frac, frac, frac) //Black -> White
        else -> RGB(1.0 - frac, 1.0 - frac, 1.0 - frac) //White -> Black
    }

    private fun fullSpectrum(range: Int, frac: Double): RGB = when (range) {
        0 -> RGB(1.0, frac, 0.0) //Red -> Yellow
        1 -> RGB(1.0 - frac, 1.0, 0.0) //Yellow -> Green
        2 -> RGB(0.0, 1.0, frac) //Green -> Cyan
        3 -> RGB(0.0, 1.0 - frac, 1.0) //Cyan -> Blue
        4 -> RGB(frac, 0.0, 1.0) //Blue -> Magenta
        5 -> RGB(1.0, 0.0, 1.0 - frac) //Magenta -> Red
        else -> throw IllegalArgumentException("Out of range: " + range)
    }
}
