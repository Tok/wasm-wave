import data.Spectrum

@SymbolName("imp_log_tick")
external public fun logTick(tick: Int)

@SymbolName("imp_ms_since_start")
external public fun msSinceStart(): Int

@SymbolName("imp_hours")
external public fun hours(): Int

@SymbolName("imp_minutes")
external public fun minutes(): Int

@SymbolName("imp_seconds")
external public fun seconds(): Int

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
external public fun spectrum_imp(): Int
public fun spectrum() = Spectrum.fromInt(spectrum_imp())
