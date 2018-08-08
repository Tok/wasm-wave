package data

import config.Constants
import data.Complex
import data.Pos
import util.MathUtil

data class Particle(var pos: Pos = Pos.default,
                    var velocity: Complex = Complex.ZERO,
                    var acceleration: Complex = Complex.ZERO) {
    fun x() = pos.x
    fun y() = pos.y

    fun move(rand1: Double, rand2: Double, rand3: Double) = with(pos) {
        if (rand1 < 0.1) {
            val mag = rand2
            val phase = rand3 * Constants.tau
            acceleration = Complex.fromMagnitudeAndPhase(mag, phase)
        }
        val velocity = velocity + acceleration
        val speed = 5.0
        val newX = x + (speed * velocity.im).toInt()
        val newY = y + (speed * velocity.re).toInt()
        jumpTo(newX, newY)
    }

    fun jumpTo(newX: Int, newY: Int) {
        pos = Pos(newX, newY)
    }

    override fun toString(): String = pos.toString() + " -> " + velocity
}
