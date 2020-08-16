package problems

import importSudokus
import timed
import java.io.File

fun main() {
        println("PE96. By solving all fifty sudoku puzzles find the sum of the 3-digit numbers found in the top left corner of each solution grid: " +
            // File("res/sudoku.txt").importSudokus().sumBy {
            //     it.solved()?.upperLeftThreeDigit ?: throw Exception("found either 0 or more than one solution")
            // } took 18.211s
            24702
        )
}