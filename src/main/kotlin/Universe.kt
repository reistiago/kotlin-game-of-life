import java.lang.StringBuilder

class Universe(private val size: Int = 10) {
    private val cells = Array(size * size) { Cell() }

    /**
     * Init alive cells in the universe
     */
    fun seedUniverse(aliveCells: List<Pair<Int, Int>>) {

        // teach cells about neighbours
        cells.forEachIndexed { i, cell ->
            val x = i % size
            val y = i / size

            cell.neighbours.addAll(this.buildNeighbours(x, y)
                .asSequence()
                .map { it.index() }
                .map { cells[it] }
                .toMutableList())
        }

        aliveCells
            .filter { it.inBounds() }
            .forEach { this.cells[it.index()].alive = true }
    }

    fun evolve() {
        val alivePerCell = cells.map { it.aliveNeighbours() }
        cells.forEachIndexed { index, cell -> cell.evolve(alivePerCell[index]) }
    }

    private fun buildNeighbours(x: Int, y: Int): List<Pair<Int, Int>> {
        return listOf(
            Pair(x - 1, y - 1), Pair(x, y - 1), Pair(x + 1, y - 1),
            Pair(x - 1, y), Pair(x + 1, y),
            Pair(x - 1, y + 1), Pair(x, y + 1), Pair(x + 1, y + 1)
        ).filter { it.inBounds() }
    }

    private fun Pair<Int, Int>.inBounds(): Boolean {
        return first in 0 until size && (second in 0 until size)
    }

    private fun Pair<Int, Int>.index(): Int {
        return this.first * size + this.second
    }

    /**
     * Pretty print the universe
     */
    override fun toString(): String {
        val sb = StringBuilder()

        cells.forEachIndexed { index, cell ->
            sb.append(cell.toString())
            val x = index % size
            if (x == size - 1) {
                sb.append(System.lineSeparator())
            }
        }

        return sb.toString()
    }
}