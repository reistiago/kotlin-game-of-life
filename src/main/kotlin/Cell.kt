class Cell(var alive: Boolean = false, var index: Int) {
    /**
     * List with all neighbours available for our cell
     */
    val neighbours: MutableList<Cell> = arrayListOf()

    fun evolve(aliveNeighbours: Int) {
        alive = when (aliveNeighbours) {
            // Dies of solitude
            0, 1 -> false
            // Survives with 2 neighbours
            2 -> this.alive
            // Either continue to exist or become alive
            3 -> true
            // Dies of overpopulation
            else -> false
        }
    }

    fun aliveNeighbours(): Int = neighbours.count { it.alive }

    override fun toString(): String {
        return if (alive) "x" else "-"
    }
}