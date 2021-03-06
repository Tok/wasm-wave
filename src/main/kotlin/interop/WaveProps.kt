package interop

import data.Spectrum

object WaveProps {
    var intensity: Double = JsImports.intensity()
    var frequency: Double = JsImports.frequency()
    var velocity: Double = JsImports.velocity()
    var spectrum: Spectrum = JsImports.spectrum()
    var particleCount = JsImports.particleCount()
    var pixelsPerSide = JsImports.pixelsPerSide()

    var lastUpdate = 0
    fun maybeUpdate(ts: Int) {
        if (ts >= lastUpdate - 1000) {
            intensity = JsImports.intensity()
            frequency = JsImports.frequency()
            velocity = JsImports.velocity()
            spectrum = JsImports.spectrum()
            particleCount = JsImports.particleCount()
            pixelsPerSide = JsImports.pixelsPerSide()
            lastUpdate = ts
        }
    }
}
