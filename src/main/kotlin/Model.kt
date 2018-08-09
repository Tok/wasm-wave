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
    var resolution = 20
    var particleCount = 0
    var pixelCount = 0

    private fun calcResolution(w: Int, h: Int): Int {
        val side = (w + h) / 2
        return (side / Style.approxPixelsPerSide).toInt()
    }

    private fun removeOrAddPaticles(center: Pos) {
        particleCount = particleCount()
        if (particleCount > particles.size) {
            particles.add(Particle(center))
        }
        if (particleCount < particles.size) {
            particles.removeAt(0)
        }
    }

    fun maybeInitialize(w: Int, h: Int) {
        fun randomPos() = Pos.random(rand(), rand(), w, h)
        if (!isInitialized) {
            this.w = w
            this.h = h
            this.center = Pos(w / 2, h / 2)
            this.resolution = calcResolution(w, h)
            this.pixelCount = (h / resolution).toInt() * (w / resolution).toInt()
            isInitialized = true
        }
        removeOrAddPaticles(center)
        /* TODO replace location.reload();
        if (this.w != w || this.h != h) {
            this.w = w
            this.h = h
            this.center = Pos(w / 2, h / 2)
            println("Size changed...")
        }
        */
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
