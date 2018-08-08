package data

import config.Constants
import config.Style
import data.Complex
import data.Pos
import util.MathUtil

data class Particle(var pos: Pos = Pos.default,
                    var velocity: Complex = Complex.ZERO,
                    var acceleration: Complex = Complex.ZERO) {
    fun x() = pos.x
    fun y() = pos.y

    private fun reset(center: Pos) {
        pos = center
        velocity = Complex.ZERO
        acceleration = Complex.ZERO
    }

    private fun isOff(w: Int, h: Int, center: Pos): Boolean =
            pos.distanceTo(center) > (w + h) / 3

    private fun changeDirection(rand1: Double, rand2: Double) {
        val mag = rand1
        val phase = rand2 * Constants.tau
        acceleration = Complex.fromMagnitudeAndPhase(mag, phase)
    }

    fun move(rand1: Double, rand2: Double, rand3: Double,
             w: Int, h: Int, center: Pos) = with(pos) {
        if (isOff(w, h, center)) {
            reset(center)
        } else {
            if (rand1 < 0.05) {
                changeDirection(rand2, rand3)
            }
            val velocity = velocity + acceleration
            val newX = x + (Style.particleSpeed * velocity.im).toInt()
            val newY = y + (Style.particleSpeed * velocity.re).toInt()
            jumpTo(newX, newY)
        }
    }

    private fun jumpTo(newX: Int, newY: Int) {
        this.pos = Pos(newX, newY)
    }

    override fun toString(): String = pos.toString() + " -> " + velocity
}
