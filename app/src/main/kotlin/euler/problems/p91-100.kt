package euler.problems

import euler.importSudokus
import euler.timed
import java.io.File

private const val PATH_PREFIX = "app/src/main/resources"

fun main()= timed {
        println("PE96. By solving all fifty sudoku puzzles find the sum of the 3-digit numbers found in the top left corner of each solution grid: " +
            File("$PATH_PREFIX/sudoku.txt").importSudokus().sumOf {
                it.solved()?.upperLeftThreeDigit ?: throw Exception("found either 0 or more than one solution")
            }
                // took 18.211s
            // 24702
        )
}