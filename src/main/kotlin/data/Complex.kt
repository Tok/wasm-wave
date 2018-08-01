package data

import util.MathUtil
import kotlinx.interop.wasm.math.*

/**
 * Originally transpiled and rearranged from: https://github.com/Tok/Erwin/tree/master/src/main/java/erwin
 * In: https://github.com/Tok/Zir-Watchface/blob/master/Wearable/src/main/kotlin/zir/teq/wearable/watchface/model/data/types/Complex.kt
 * Trough: https://github.com/Tok/Q-gress/blob/master/src/util/data/Complex.kt
 */
data class Complex(val re: Double, val im: Double = 0.0) {
    constructor(real: Int, imaginary: Int = 0) : this(real.toDouble(), imaginary.toDouble())

    val magnitude: Double = Math.sqrt(addSquares(re, im)).toDouble()
    val mag: Double = magnitude
    val magnitude2: Double = addSquares(re, im)
    val phase = Math.atan2(im.toDouble(), re.toDouble())
    val modulus = magnitude

    fun copyWithNewMagnitude(mag: Double) = Complex.fromMagnitudeAndPhase(mag, this.phase)

    fun negate() = Complex(-re, -im)
    fun conjugate() = Complex(re, -im)
    fun reverse() = Complex(-re, im)
    operator fun not() = negate()
    operator fun plus(c: Complex) = Complex(re + c.re, im + c.im)
    operator fun plus(d: Double) = Complex(re + d, im)
    operator fun minus(c: Complex) = Complex(re - c.re, im - c.im)
    operator fun minus(d: Double) = Complex(re - d, im)
    operator fun times(c: Complex) = Complex(re * c.re - im * c.im, re * c.im + im * c.re)
    operator fun div(c: Complex): Complex {
        if (c.re == 0.0) throw IllegalArgumentException("Real part is 0.")
        if (c.im == 0.0) throw IllegalArgumentException("Imaginary part is 0.")
        val d = addSquares(c.re, c.im).toDouble()
        return Complex((re * c.re + im * c.im) / d, (im * c.re - re * c.im) / d)
    }

    private fun addSquares(re: Double, im: Double) = (re * re + im * im)

    override fun toString(): String {
        return when (this) {
            I -> "i"
            Complex(re) -> re.toString()
            Complex(im) -> im.toString() + "*i"
            else -> {
                val imString = if (im < 0F) "-" + -im else "+" + im
                return re.toString() + imString + "*i"
            }
        }
    }

    companion object {
        val I = Complex(0.0, 1.0)
        val ZERO = Complex(0.0, 0.0)
        val ONE = Complex(1.0, 0.0)
        fun selectStronger(first: Complex, second: Complex) = if (first.mag < second.mag) first else second
        fun fromImaginary(imaginary: Double) = Complex(0.0, imaginary)
        fun fromImaginaryInt(imaginary: Int) = Complex(0.0, imaginary.toDouble())
        fun valueOf(magnitude: Double, phase: Double) = fromMagnitudeAndPhase(magnitude, phase)

        fun fromMagnitudeAndPhase(magnitude: Double, phase: Double): Complex {
            val im = magnitude * Math.cos(phase).toDouble()
            val re = magnitude * Math.sin(phase).toDouble()
            return Complex(im, re)
        }
    }
}
