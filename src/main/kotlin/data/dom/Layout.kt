package data.dom

import data.Pos;
import kotlinx.interop.wasm.dom.DOMRect

open class Layout(val rect: DOMRect) {
    val left = rect.getInt("left")
    val right = rect.getInt("right")
    val top = rect.getInt("top")
    val bottom = rect.getInt("bottom")

    val w = right - left
    val h = bottom - top
    val w_center = w / 2
    val h_center = h / 2

    val first = Pos(w / 3, h_center)
    val second = Pos(w  / 3 * 2, h_center)
}
