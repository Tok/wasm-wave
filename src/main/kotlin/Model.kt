import data.Particle
import data.Pos

object Model {
    var isInitialized = false
    fun push(t: Int) {
        update(t)
        move()
    }

    var first = Particle.create("first")
    var second = Particle.create("second")
    var third = Particle.create("third")
    fun maybeInitialize(w: Int, hCenter: Int) {
        if (!isInitialized) {
            first = first.moveTo(w / 3, hCenter)
            second = second.moveTo((w * 2) / 3, hCenter)
            third = third.moveTo(w / 2, hCenter)
            isInitialized = true
        }
    }

    fun move() {
        first = first.move(rand(), rand())
        second = second.move(rand(), rand())
        third = third.move(rand(), rand())
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
