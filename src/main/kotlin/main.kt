import kotlinx.interop.wasm.dom.asCanvas
import kotlinx.interop.wasm.dom.Canvas
import kotlinx.interop.wasm.dom.document
import kotlinx.interop.wasm.dom.setInterval

fun loop(can: Canvas) {
    Model.push(0)
    View(can).render()
}

fun main(args: Array<String>) {
    val can = document.getElementById("canvas").asCanvas
    val intervalMs = 50
    setInterval(intervalMs) {
        loop(can)
    }
}
