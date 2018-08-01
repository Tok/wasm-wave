object Model {
    var n = 0
    fun push(i: Int) {
        n = n + 1
        tick(i.toString())
    }
}

@SymbolName("imported_tick")
external public fun tick(msg: String)
