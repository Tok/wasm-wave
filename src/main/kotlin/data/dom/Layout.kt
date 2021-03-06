package data.dom

import data.Pos
import kotlinx.interop.wasm.dom.DOMRect

open class Layout(val rect: DOMRect) {
    val w = rect.getInt("right") - rect.getInt("left")
    val h = rect.getInt("bottom") - rect.getInt("top")
    val center = Pos(w / 2, h / 2)
}
