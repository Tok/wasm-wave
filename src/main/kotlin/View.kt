import config.Style
import config.Wave
import data.Complex
import data.Pos
import data.dom.Layout
import kotlinx.interop.wasm.dom.Canvas
import util.ColorUtil
import util.WaveCalc

class View(private val can: Canvas) : Layout(can.getBoundingClientRect()) {
    private val context = can.getContext("2d")

    private fun drawWaves(i: Int) = with (Model) {
        context.beginPath()
        val t = i * Wave.velocity
        val yRange = 0..(h - resolution)
        val xRange = 0..(w - resolution)
        for (y in yRange step resolution) {
            for (x in xRange step resolution) {
                drawPixel(x, y, t)
            }
        }
    }

    private fun drawPixel(x: Int, y: Int, t: Double) {
        val pos = Pos(x, y)
        val waves: List<Complex> = Model.particles.map {
            WaveCalc.calc(pos, it.pos, t.toDouble())
        }
        val waveSum = waves.fold(Complex.ZERO) { sum, el -> sum + el }
        context.fillStyle = ColorUtil.getColor(waveSum)
        context.fillRect(x, y, Model.resolution, Model.resolution)
    }

    private fun drawParticles() {
        context.fillStyle = ColorUtil.WHITE
        val w = Model.resolution
        val r = w / 2
        Model.particles.forEach {
            context.fillRect(it.x() - r, it.y() - r, w, w)
        }
    }

    private fun clear() = with(context) {
        fillStyle = Style.backgroundColor
        fillRect(0, 0, w, h)
    }

    private fun printTexts() {
        fun print(text: String, topOffset: Int) = with(context) {
            fillStyle = Style.fontColor
            fillText(text, 2, topOffset, 100)
        }
        with (Model) {
            print("FPS: ${calcFps()}", 10)
            print("Particles: $particleCount", 20)
            print("Pixels: $pixelCount", 30)
            print("Tick: $tick", 40)
            //print(Model.currentTime(), 00)
        }
    }

    fun render() {
        Model.maybeInitialize(w, h)

        clear()
        drawWaves(Model.tick)
        drawParticles()
        printTexts()
    }
}
