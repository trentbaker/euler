package euler.problems

import euler.EulerProblem
import java.math.BigInteger

object Problem15 : EulerProblem() {
    override val name = "Lattice Paths"
    private fun countRoutesInSquareGrid(dimension: Int): BigInteger {
        var paths = BigInteger.ONE
        for (i in 0 until dimension) {
            paths *= (2 * dimension).toBigInteger() - i.toBigInteger()
            paths /= (i + 1).toBigInteger()
        }
        return paths
    }

    override fun exampleProblem(): String = buildString {
        append("Starting in the top left corner of a 2x2 grid, and only being able to move to the right and down, how many such routes are there: ")
        append(countRoutesInSquareGrid(2))
    }

    override fun realProblem(): String = buildString {
        append("Starting in the top left corner of a 20x20 grid, and only being able to move to the right and down, how many such routes are there: ")
        append(countRoutesInSquareGrid(20))
    }
}

fun main() {
    println(Problem15.solve())
}