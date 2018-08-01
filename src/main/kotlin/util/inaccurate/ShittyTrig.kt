package util.inaccurate

import kotlinx.interop.wasm.math.Math

/* Trigonometry functions mostly transcoded from
 * https://stackoverflow.com/a/22824049
 */
object ShittyTrig {
    val trigIterations = 50 //tune this...

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
}
