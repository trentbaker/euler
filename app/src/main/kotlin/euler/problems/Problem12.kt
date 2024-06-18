package euler.problems

import euler.EulerProblem
import kotlin.math.sqrt

object Problem12 : EulerProblem() {
    override val name = "Highly Divisible Triangular Number"

    val triangleNumbers = generateSequence(1) { it + 1 }.map {
        0 until it
    }.map { it.sum() }

    fun factorsOf(input: Int): List<Int> {
        val output = mutableListOf<Int>()
        IntRange(0, sqrt(input.toDouble()).toInt()).forEach { current ->
            if (current != 0 && input % current == 0) {
                output.add(current) // add the found factor
                output.add(input / current) // add the inferred factor
            }
        }
        return output.distinct().sorted()
    }

    override fun exampleProblem(): String = buildString {
        append("Find the value of the first triangle number to have over five divisors: ")
        append(triangleNumbers.first { factorsOf(it).size > 5 })
    }

    override fun realProblem(): String = buildString {
        append("Find the value of the first triangle number to have over five hundred divisors: ")
        append(triangleNumbers.first { factorsOf(it).size > 500 })
    }
}

fun main() {
    println(Problem12.solve())
}