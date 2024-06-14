package euler.problems

import euler.EulerProblem

object Problem5 : EulerProblem() {
    private fun smallestDivisbleBy(input: IntRange): Int {
        var current = input.first.toDouble()
        while (input.any { current % it != 0.0 })
            current += input.filter { current % it == 0.0 }.maxOrNull()
                ?: 1
        return current.toInt()
    }

    override fun exampleProblem() = buildString {
        append("Find the smallest positive number that is evenly divisible by all of the numbers from 1 to 10: ")
        append(smallestDivisbleBy(1 .. 10))
    }
    override fun realProblem() = buildString {
        append("Find the smallest positive number that is evenly divisible by all of the numbers from 1 to 20: ")
        append(smallestDivisbleBy(1 .. 20))
    }
}

fun main() {
    Problem5.execute()
}