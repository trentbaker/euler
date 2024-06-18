package euler.problems

import euler.EulerProblem

object Problem28 : EulerProblem() {
    override val name = "Number Spiral Diagonals"

    private class NumberSpiral(
        private val dimension: Int,
    ) {
        init {
            require(dimension % 2 == 1) { "don't know how to spiral on an even grid" }
        }

        private val possibleNumbers = (2..dimension * dimension)

        val diagonalNumbers by lazy {
            var chunkSize = 1
            var skipped = 0
            var savedAtSize = 0

            val output = mutableListOf<Int>()

            possibleNumbers.forEach {
                val skipping = skipped < chunkSize
                if (skipping) {
                    skipped++
                } else {
                    output += it
                    savedAtSize++
                    skipped = 0
                    if (savedAtSize >= 4) {
                        savedAtSize = 0
                        chunkSize += 2
                    }
                }

            }

            listOf(1) + output
        }
    }

    override fun exampleProblem(): String = buildString {
        append("Find the sum of the diagonals of a 5x5 number spiral: ")
        val spiral = NumberSpiral(5)
        append(spiral.diagonalNumbers.sum())
    }

    override fun realProblem(): String = buildString {
        append("Find the sum of the diagonals of a 1001x1001 number spiral: ")
        val spiral = NumberSpiral(1001)
        append(spiral.diagonalNumbers.sum())
    }
}

fun main() {
    println(Problem28.solve())
}