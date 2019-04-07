package interop

import data.Spectrum

object JsImports {
    @SymbolName("imp_log_tick")
    external public fun logTick(tick: Int)

    @SymbolName("imp_ms_since_start")
    external public fun msSinceStart(): Int

    @SymbolName("imp_random")
    external public fun rand(): Double

    @SymbolName("imp_particle_count")
    external public fun particleCount(): Int
    @SymbolName("imp_pixels_per_side")
    external public fun pixelsPerSide(): Int

    @SymbolName("imp_frequency")
    external public fun frequency(): Double
    @SymbolName("imp_velocity")
    external public fun velocity(): Double
    @SymbolName("imp_intensity")
    external public fun intensity(): Double

    @SymbolName("imp_spectrum")
    external private fun spectrum_impl(): Int
    public fun spectrum() = Spectrum.fromInt(spectrum_impl())

    @SymbolName("imp_hours")
    external private fun hours(): Int
    @SymbolName("imp_minutes")
    external private fun minutes(): Int
    @SymbolName("imp_seconds")
    external private fun seconds(): Int
    public fun currentTime(): String {
        fun pad(t: Int) = t.toString().padStart(2, '0')
        val hh = pad(JsImports.hours())
        val mm = pad(JsImports.minutes())
        val ss = pad(JsImports.seconds())
        return "$hh:$mm:$ss"
    }
}
