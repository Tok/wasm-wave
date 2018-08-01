package util

import kotlinx.interop.wasm.math.Math
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object MathUtil {
    val useInterop = true

    fun abs(n: Double): Double = if (n >= 0.0) n else -n

    fun atan2(y: Double, x: Double): Double =
            if (useInterop) Math.atan2(y, x) else atan2(y, x)

    fun cos(n: Double): Double =
            if (useInterop) Math.cos(n) else cos(n)

    fun min(first: Double, second: Double): Double =
            if (first < second) first else second

    fun max(first: Double, second: Double): Double =
            if (first > second) first else second

    fun sin(n: Double): Double =
            if (useInterop) Math.sin(n) else sin(n)

    fun sqrt(n: Double): Double =
            if (useInterop) Math.sqrt(n) else sqrt(n)
}
