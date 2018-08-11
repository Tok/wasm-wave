package util

import util.inaccurate.ShittyMath
import util.inaccurate.ShittyTrig
import interop.JsImports
import kotlinx.interop.wasm.math.Math

object MathUtil {
    val useImpreciseMath = true
    val useImpreciseTrig = true

    fun random() = JsImports.rand()

    fun abs(n: Double): Double =
            if (n >= 0.0) n else -n

    fun min(first: Double, second: Double): Double =
            if (first < second) first else second

    fun max(first: Double, second: Double): Double =
            if (first > second) first else second

    fun sqrt(n: Double): Double =
            if (useImpreciseMath) ShittyMath.sqrt(n) else Math.sqrt(n)

    fun pow(y: Double, x: Double): Double =
            if (useImpreciseMath) ShittyMath.pow(y, x) else Math.pow(y, x)

    fun atan2(y: Double, x: Double): Double =
            if (useImpreciseTrig) ShittyTrig.atan2(y, x) else Math.atan2(y, x)

    fun cos(n: Double): Double =
            if (useImpreciseTrig) ShittyTrig.sin(n) else Math.cos(n)

    fun sin(n: Double): Double =
            if (useImpreciseTrig) ShittyTrig.cos(n) else Math.sin(n)

    fun fact(n: Double): Double = ShittyMath.fact(n)
}
