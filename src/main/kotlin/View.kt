import config.Style
import config.Wave
import data.Complex
import data.Pos
import data.Spectrum
import data.dom.Layout
import kotlinx.interop.wasm.dom.Canvas
import util.ColorUtil
import util.WaveCalc

class View(private val can: Canvas) : Layout(can.getBoundingClientRect()) {
    private val context = can.getContext("2d")

    private fun drawWaves(i: Int) = with(Model) {
        val topOffset = ((h % resolution) / 2).toInt()
        val leftOffset = ((w % resolution) / 2).toInt()
        val t = i * velocity()
        val yRange = topOffset..(h - resolution)
        val xRange = leftOffset..(w - resolution)
        context.beginPath()
        for (y in yRange step resolution) {
            for (x in xRange step resolution) {
                drawPixel(x, y, t, topOffset, leftOffset)
            }
        }
    }

    private fun drawPixel(x: Int, y: Int, t: Double,
                          topOffset: Int = 0, leftOffset: Int = 0) = with(Model) {
        val pos = Pos(x, y)
        val waves: List<Complex> = particles.map {
            WaveCalc.calc(pos, it.pos, t.toDouble(), frequency(), intensity())
        }
        val waveSum = waves.fold(Complex.ZERO) { sum, el -> sum + el }
        context.fillStyle = ColorUtil.getColor(spectrum(), waveSum)
        val (xx, xRes) = when {
            x <= leftOffset -> (x - leftOffset) to (resolution + leftOffset) //first col
            x >= (w - (2 * resolution)) -> x to (resolution + leftOffset) //last col
            else -> x to resolution
        }
        val (yy, yRes) = when {
            y <= topOffset -> (y - topOffset) to (resolution + topOffset) //first row
            y > (h - (2 * resolution)) -> y to (resolution + topOffset) //last row
            else -> y to resolution
        }
        context.fillRect(xx, yy, xRes, yRes)
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
        with(Model) {
            print("FPS: ${calcFps()}", 10)
            print("Pixels: $pixelCount", 20)
            print("Tick: $tick", 30)
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
