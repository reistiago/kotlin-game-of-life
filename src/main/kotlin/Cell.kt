class Cell(var alive: Boolean = false) {
    /**
     * List with all neighbours available for our cell
     */
    val neighbours: MutableList<Cell> = arrayListOf()

    fun evolve(aliveNeighbours: Int) {
        alive = when (aliveNeighbours) {
            // Dies of solitude
            0, 1 -> false
            // Keeps on living
            2, 3 -> true
            // Dies of overpopulation
            else -> false
        }
    }

    fun aliveNeighbours(): Int = neighbours.count { it.alive }

    override fun toString(): String {
        return if (alive) "x" else "-"
    }
}