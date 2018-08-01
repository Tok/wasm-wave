import kotlinx.interop.wasm.dom.asCanvas
import kotlinx.interop.wasm.dom.Canvas
import kotlinx.interop.wasm.dom.document
import kotlinx.interop.wasm.dom.setInterval

fun loop(canvas: Canvas) {
    Model.push(0)
    View(canvas).render()
}

fun main(args: Array<String>) {
    val canvas = document.getElementById("canvas").asCanvas
    val intervalMs = 50
    setInterval(intervalMs) {
        loop(canvas)
    }
}
