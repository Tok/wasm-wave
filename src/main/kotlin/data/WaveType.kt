package data

enum class WaveType(val n: Int) {
    LIGHT(0),
    MATTER(1);

    companion object {
        val default = LIGHT
        fun fromInt(n: Int): WaveType = values().find { it.ordinal == n } ?: default
    }
}
