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

    private fun print(text: String, topOffset: Int) = with(context) {
        fillStyle = Style.fontColor
        fillText(text, 2, topOffset, 100)
    }

    fun printTime(time: String) = print(time, 10)
    fun printTick(tick: Int) = print("Tick: $tick", 20)
    fun printFps(fps: Int) = print("FPS: $fps", 30)
    fun printPos1() = print("Pos 1: ${Model.first}", 40)
    fun printPos2() = print("Pos 2: ${Model.second}", 50)
    fun printPos3() = print("Pos 3: ${Model.third}", 60)

    fun drawWaves(i: Int) {
        context.beginPath()
        val t = i * Wave.velocity
        for (y in 0..super.h step Style.resolution) {
            for (x in 0..super.w step Style.resolution) {
                drawPixel(x, y, t)
            }
        }
    }

    fun drawPos() {
        context.fillStyle = ColorUtil.WHITE
        val r = 3
        val w = 2 * r
        context.fillRect(Model.first.x - r, Model.first.y - r, w, w)
        context.fillRect(Model.second.x - r, Model.second.y - r, w, w)
        context.fillRect(Model.third.x - r, Model.third.y - r, w, w)
    }

    fun drawPixel(x: Int, y: Int, t: Double) {
        val pos = Pos(x, y)
        val firstWave = WaveCalc.calc(pos, Model.first.pos, t.toDouble())
        val secondWave = WaveCalc.calc(pos, Model.second.pos, t.toDouble())
        val thirdWave = WaveCalc.calc(pos, Model.third.pos, t.toDouble())
        val waveSum = firstWave + secondWave + thirdWave
        val wave = Complex.valueOf(waveSum.magnitude / 3, waveSum.phase)
        context.fillStyle = ColorUtil.getColor(wave)
        context.fillRect(x, y, Style.resolution, Style.resolution)
    }

    fun clear() = with(context) {
        fillStyle = Style.backgroundColor
        fillRect(0, 0, super.w, super.h)
    }

    fun render() {
        Model.maybeInitialize(w, hCenter)

        //clear()
        drawWaves(Model.tick)
        drawPos()
        printTime(Model.currentTime())
        printTick(Model.tick)
        printFps(Model.calcFps())
        printPos1()
        printPos2()
        printPos3()
    }
}
