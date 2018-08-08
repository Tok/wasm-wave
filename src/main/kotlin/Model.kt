import config.Style
import data.Particle
import data.Pos

object Model {
    var isInitialized = false
    fun push(t: Int) {
        update(t)
        move()
    }

    val particles = mutableListOf<Particle>()
    var w = 720
    var h = 720
    var center = Pos(w / 2, h / 2)

    fun maybeInitialize(w: Int, h: Int) {
        fun randomPos() = Pos.random(rand(), rand(), w, h)
        if (!isInitialized) {
            this.w = w
            this.h = h
            this.center = Pos(w / 2, h / 2)
            (1..Style.particleCount).forEach {
                particles.add(Particle(center))
            }
            isInitialized = true
        }
    }

    fun move() = particles.forEach {
        it.move(rand(), rand(), rand(), w, h, center)
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
