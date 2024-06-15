package euler.problems

import euler.EulerProblem

object Problem1 : EulerProblem() {
     override fun exampleProblem(): String =
        "Find the sum of all multiples of 3 or 5 below 10: ${
            (0 until 10).filter { it % 3 == 0 || it % 5 == 0 }.sum()
        }"

     override fun realProblem(): String =
        "Find the sum of all multiples of 3 or 5 below 1000: ${
            (0 until 1000).filter { it % 3 == 0 || it % 5 == 0 }.sum()
        }"
}

fun main() {
    println(Problem1.solve())
}
