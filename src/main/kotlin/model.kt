object Model {
    var tick = 0
    private var lastTs = 0
    private var ts = 0
    fun push(t: Int) {
        tick = t
        lastTs = ts
        ts = msSinceStart()
        //logTick(tick)
    }

    private var currentFps = 0
    fun calcFps(): Int {
        if (tick % 10 == 0) {
            currentFps = (1000 / (ts - lastTs)).toInt()
        }
        return currentFps
    }

    fun currentTime(): String {
        fun pad(t: Int) = t.toString().padStart(2, '0')
        val hh = pad(hours())
        val mm = pad(minutes())
        val ss = pad(seconds())
        return "$hh:$mm:$ss"
    }
}

@SymbolName("imported_log_tick")
external public fun logTick(tick: Int)

@SymbolName("imported_ms_since_start")
external public fun msSinceStart(): Int

@SymbolName("imported_hours")
external public fun hours(): Int

@SymbolName("imported_minutes")
external public fun minutes(): Int

@SymbolName("imported_seconds")
external public fun seconds(): Int
