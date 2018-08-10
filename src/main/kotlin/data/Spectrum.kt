package data

enum class Spectrum(val n: Int) {
    FULL(0),
    BW(1);

    companion object {
        val default = FULL
        fun fromInt(n: Int): Spectrum = values().find { it.ordinal == n } ?: default
    }
}
