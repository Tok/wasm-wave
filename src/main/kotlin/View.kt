import config.Style
import config.Wave
import data.Complex
import data.Pos
import data.dom.Layout
import kotlinx.interop.wasm.dom.Canvas
import util.ColorUtil
import util.WaveCalc

class View(val can: Canvas) : Layout(can.getBoundingClientRect()) {
    val context = can.getContext("2d")

    fun drawWaves(i: Int) {
        context.beginPath()
        val t = i * Wave.velocity
        for (y in (0..(Model.h-Model.resolution)) step Model.resolution) {
            for (x in (0..(Model.w-Model.resolution)) step Model.resolution) {
                drawPixel(x, y, t)
            }
        }
    }

    fun drawParticles() {
        context.fillStyle = ColorUtil.WHITE
        val w = Style.particleW
        val r = Style.particleW / 2
        Model.particles.forEach {
            context.fillRect(it.x() - r, it.y() - r, w, w)
        }
    }

    fun drawPixel(x: Int, y: Int, t: Double) {
        val pos = Pos(x, y)
        val waves: List<Complex> = Model.particles.map {
            WaveCalc.calc(pos, it.pos, t.toDouble())
        }
        val waveSum = waves.fold(Complex.ZERO) { sum, el -> sum + el }
        context.fillStyle = ColorUtil.getColor(waveSum)
        context.fillRect(x, y, Model.resolution, Model.resolution)
    }

    fun clear() = with(context) {
        fillStyle = Style.backgroundColor
        fillRect(0, 0, w, h)
    }

    private fun printTexts() {
        fun print(text: String, topOffset: Int) = with(context) {
            fillStyle = Style.fontColor
            fillText(text, 2, topOffset, 100)
        }
        print("FPS: ${Model.calcFps()}", 10)
        print("Particles: ${Model.particleCount}", 20)
        print("Pixels: ${Model.pixelCount}", 30)
        print("Tick: ${Model.tick}", 40)
        //print(Model.currentTime(), 00)
    }

    fun render() {
        Model.maybeInitialize(w, h)

        clear()
        drawWaves(Model.tick)
        drawParticles()
        printTexts()
    }
}
