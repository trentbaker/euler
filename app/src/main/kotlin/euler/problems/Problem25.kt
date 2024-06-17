package euler.problems

import euler.EulerProblem

object Problem25 : EulerProblem() {
    override val name = "1000-digit Fibonacci Number"

    override fun exampleProblem(): String = buildString {
        append("Find the index of the first fibonacci number to consist of 3 digits: ")
        append(Problem2.fibonacciSequence.indexOfFirst { it.toString().length == 3 })
    }

    override fun realProblem(): String = buildString {
        append("Find the index of the first fibonacci number to consist of 1000 digits: ")
        append(Problem2.fibonacciSequence.indexOfFirst { it.toString().length == 1000 })
    }
}

fun main() {
    println(Problem25.solve())
}