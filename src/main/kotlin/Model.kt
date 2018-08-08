import data.Pos

object Model {
    var isInitialized = false
    fun push(t: Int) {
        update(t)
        move()
    }

    var first = Pos.default
    var second = Pos.default
    fun maybeInitialize(w: Int, hCenter: Int) {
        if (!isInitialized) {
            first = Pos(w / 3, hCenter)
            second = Pos((w * 2) / 3, hCenter)
            isInitialized = true
        }
    }

    fun move() {
        first = first.move(rand(), rand())
        second = second.move(rand(), rand())
    }

    var tick = 0
    private var lastTs = 0
    private var ts = 0
    private fun update(t: Int) {
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