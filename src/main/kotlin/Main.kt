import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.long
import java.io.File

class Main : CliktCommand() {
    private val output: String by option(help = "Output file to use").default("out.txt")
    private val size: Int by option(help = "Size of the board").int().default(10)
    private val delay: Long by option(help = "Delay world iteration in millis").long().default(100)

    override fun run() {
        val u = Universe(size)
        val file = File(output)
        // block
        // val seed = listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1))

        // beehive
        //val seed = listOf(Pair(5, 0), Pair(4, 1), Pair(4, 2), Pair(5, 3), Pair(6, 2), Pair(6, 1))

        // R-pentomino
        // val seed = listOf(Pair(5, 5), Pair(6, 5), Pair(6, 6), Pair(6, 4), Pair(7, 4))

        // blinker
        val seed = listOf(Pair(3, 1), Pair(2, 1), Pair(4, 1))

        u.seedUniverse(seed)
        print(u)

        while (true) {
            file.printWriter().use { out -> out.println(u.toString()) }
            u.evolve()
            if (delay != 0L) {
                Thread.sleep(delay)
            }
        }
    }
}

fun main(args: Array<String>) = Main().main(args)

