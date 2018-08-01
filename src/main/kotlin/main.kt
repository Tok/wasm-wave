import config.Style
import kotlinx.interop.wasm.dom.asCanvas
import kotlinx.interop.wasm.dom.Canvas
import kotlinx.interop.wasm.dom.document
import kotlinx.interop.wasm.dom.setInterval

fun loop(can: Canvas, tick: Int) {
    Model.push(tick)
    View(can).render()
}

fun main(args: Array<String>) {
    val can = document.getElementById("canvas").asCanvas
    val intervalMs = 1000 / Style.fpsLimit
    var t = 0
    setInterval(intervalMs) {
        t = t + 1
        loop(can, t)
    }
}
