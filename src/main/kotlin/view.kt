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
        //TODO set font
        fillStyle = Style.FONT_COLOR
        val textlength = 50
        fillText(tick.toString(), 2, 10, textlength)
    }

    //divisors of 720: 1,2,3,4,5,6,8,9,10,12,15,16,18,20,24,30,36,40
    val RESOLUTION = 20

    fun drawWaves(i: Int) {
        context.beginPath()
        val t = i * Wave.VELOCITY
        for (y in 0..super.h step RESOLUTION) {
            for (x in 0..super.w step RESOLUTION) {
                drawPixel(x, y, t)
            }
        }
    }

    fun drawPixel(x: Int, y: Int, t: Double) {
        val pos = Pos(x, y)
        val freq = Wave.FREQUENCY
        val mass = Wave.MASS
        val firstWave = WaveCalc.calc(pos, first, t.toDouble(), freq, mass)
        val secondWave = WaveCalc.calc(pos, second, t.toDouble(), freq, mass)
        val waveSum = firstWave + secondWave
        val wave = Complex.valueOf(waveSum.magnitude * 0.5, waveSum.phase)
        context.fillStyle = ColorUtil.getColor(wave)
        context.fillRect(x, y, RESOLUTION, RESOLUTION)
    }

    fun clean() {
        context.fillStyle = Style.BACKGROUND_COLOR
        context.fillRect(0, 0, super.w, super.h)
    }

    fun render() {
        clean()
        val tick = Model.n
        drawWaves(tick)
        printTick(tick)
    }
}
