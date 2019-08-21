import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.long
import java.io.File

class Main : CliktCommand() {
    private val output: String by option(help = "Output file to use").default("out.txt")
    private val size: Int by option(help = "Size of the board").int().default(10)
    private val delay: Long by option(help = "Delay world iteration in millis").long().default(0)

    override fun run() {
        val u = Universe(size)
        val file = File(output)

        u.seedUniverse(listOf(Pair(0, 0), Pair(1, 0), Pair(0, 1), Pair(1, 1)))

        while (true) {
            file.printWriter().use { out -> out.println(u.toString()) }
            u.evolve()
            print(".")
            if (delay != 0L) {
                Thread.sleep(delay)
            }
        }
    }
}

fun main(args: Array<String>) = Main().main(args)

