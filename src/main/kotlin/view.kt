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

    fun printTick(tick: Int) = with(context) {
        fillStyle = Style.fontColor
        fillText(tick.toString(), 2, 10, 50)
    }

    fun drawWaves(i: Int) {
        context.beginPath()
        val t = i * Wave.velocity
        for (y in 0..super.h step Style.resolution) {
            for (x in 0..super.w step Style.resolution) {
                drawPixel(x, y, t)
            }
        }
    }

    fun drawPixel(x: Int, y: Int, t: Double) {
        val pos = Pos(x, y)
        val firstWave = WaveCalc.calc(pos, first, t.toDouble())
        val secondWave = WaveCalc.calc(pos, second, t.toDouble())
        val waveSum = firstWave + secondWave
        val wave = Complex.valueOf(waveSum.magnitude * 0.5, waveSum.phase)
        context.fillStyle = ColorUtil.getColor(wave)
        context.fillRect(x, y, Style.resolution, Style.resolution)
    }

    fun clear() = with(context) {
        fillStyle = Style.backgroundColor
        fillRect(0, 0, super.w, super.h)
    }

    fun render() {
        //clear()
        val tick = Model.n
        drawWaves(tick)
        printTick(tick)
    }
}
