package euler.problems

import euler.EulerProblem

object Problem34 : EulerProblem() {
    override val name = "Digit Factorials"

    // converted this from a solution 5+ years old
    // there is some relation to Problem 74, but I have forgotten how the solutions are related
    val digitFactorialLoopFlags = listOf(
        1 to listOf(),
        2 to listOf(),
        145 to listOf(),
        40585 to listOf(),

        0 to listOf(1),
        871 to listOf(45361),
        45361 to listOf(871),
        872 to listOf(45362),
        45362 to listOf(872),

        169 to listOf(363601, 1454),
        1454 to listOf(169, 363601),
        363601 to listOf(1454, 169)
    ).map { it.first.toBigInteger() to it.second.map { it.toBigInteger() } }

    override fun realProblem(): String = buildString {
        append("Find the sum of all numbers which are equal to the sum of the factorial of their digits: ")
        val result = digitFactorialLoopFlags
            .filter { it.second.isEmpty() && it.first > 3.toBigInteger() }
            .fold(0) { acc, pair -> acc + pair.first.toInt() }
        append(result)
    }
}

fun main() {
    println(Problem34.solve())
}