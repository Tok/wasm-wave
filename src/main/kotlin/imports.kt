import data.Spectrum
import data.WaveType

@SymbolName("imp_log_tick")
external public fun imp_logTick(tick: Int)

@SymbolName("imp_ms_since_start")
external public fun imp_msSinceStart(): Int

@SymbolName("imp_hours")
external public fun imp_hours(): Int

@SymbolName("imp_minutes")
external public fun imp_minutes(): Int

@SymbolName("imp_seconds")
external public fun imp_seconds(): Int

@SymbolName("imp_random")
external public fun imp_rand(): Double

@SymbolName("imp_particle_count")
external public fun imp_particleCount(): Int

@SymbolName("imp_pixels_per_side")
external public fun imp_pixelsPerSide(): Int

@SymbolName("imp_frequency")
external public fun imp_frequency(): Double

@SymbolName("imp_velocity")
external public fun imp_velocity(): Double

@SymbolName("imp_intensity")
external public fun imp_intensity(): Double

@SymbolName("imp_spectrum")
external public fun imp_spectrum_impl(): Int
public fun imp_spectrum() = Spectrum.fromInt(imp_spectrum_impl())

@SymbolName("imp_wave_type")
external public fun imp_waveType_impl(): Int
public fun imp_waveType() = WaveType.fromInt(imp_waveType_impl())
