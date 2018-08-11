package interop

import data.Spectrum
import data.WaveType

object WaveProps {
    var intensity: Double = JsImports.intensity()
    var frequency: Double = JsImports.frequency()
    var velocity: Double = JsImports.velocity()
    var spectrum: Spectrum = JsImports.spectrum()
    var waveType: WaveType = JsImports.waveType()
    var particleCount = JsImports.particleCount()
    var pixelsPerSide = JsImports.pixelsPerSide()
    fun update() {
        intensity = JsImports.intensity()
        frequency = JsImports.frequency()
        velocity = JsImports.velocity()
        spectrum = JsImports.spectrum()
        waveType = JsImports.waveType()
        particleCount = JsImports.particleCount()
        pixelsPerSide = JsImports.pixelsPerSide()
    }
}
