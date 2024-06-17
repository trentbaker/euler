package euler.problems

import euler.EulerProblem

object Problem23 : EulerProblem() {
    override val name = "Non-Abundant Sums"

    private fun Int.isAbundant() = Problem12.factorsOf(this).dropLast(1).sum() > this

    private fun List<Int>.cannotSumFromAbundant() = this.filter { it.isAbundant() }.let { abundants ->
        this.filter { current ->
            abundants.map { current - it }.none { abundants.contains(it) }
        }
    }

    override fun realProblem(): String = buildString {
        append("Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers: ")
        val result = IntRange(0, 28123).toList().cannotSumFromAbundant().sum()
        append(result)
    }
}

fun main() {
    println(Problem23.solve())
}