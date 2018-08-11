import config.Style
import data.Particle
import data.Pos
import data.Spectrum
import data.WaveType
import interop.JsImports
import interop.WaveProps
import util.MathUtil

object Model {
    val particles = mutableListOf<Particle>()

    var resolution = 20
    var pixelCount = 0

    private var w = 720
    private var h = 720
    private var center = Pos(w / 2, h / 2)
    fun push(t: Int) {
        if (WaveProps.particleCount > 0) {
            WaveProps.maybeUpdate(ts)
            update(t)
            move()
        }
    }

    private fun calcResolution(w: Int, h: Int): Int {
        val averageSide = (w + h) / 2
        return (averageSide / WaveProps.pixelsPerSide).toInt()
    }

    private fun removeOrAddPaticles(center: Pos) {
        if (WaveProps.particleCount > particles.size) {
            particles.add(Particle(center))
        }
        if (WaveProps.particleCount < particles.size) {
            particles.removeAt(0)
        }
    }

    var isInitialized = false
    fun maybeInitialize(w: Int, h: Int) {
        val randX = MathUtil.random()
        val randY = MathUtil.random()
        fun randomPos() = Pos.random(randX, randY, w, h)
        if (!isInitialized) {
            this.w = w
            this.h = h
            this.center = Pos(w / 2, h / 2)
            isInitialized = true
        }
        this.resolution = calcResolution(w, h)
        this.pixelCount = (h / resolution).toInt() * (w / resolution).toInt()
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

    private fun move() = particles.forEach {
        val rand1 = MathUtil.random()
        val rand2 = MathUtil.random()
        val rand3 = MathUtil.random()
        it.move(rand1, rand2, rand3, w, h, center)
    }

    var tick = 0
    private var lastTs = 0
    private var ts = 0
    private fun update(t: Int) {
        tick = t
        lastTs = ts
        ts = JsImports.msSinceStart()
        //logTick(tick)
    }

    private var currentFps = 0
    fun calcFps(): Int {
        if (tick % 10 == 0) {
            currentFps = (1000 / (ts - lastTs)).toInt()
        }
        return currentFps
    }
}
