package euler.problems

import euler.EulerProblem

object Problem21 : EulerProblem() {
    override val name = "Amicable Numbers"

    private fun amicableNumbersBelow(max: Int) = IntRange(0, max).map { it to Problem12.factorsOf(it)
        .dropLast(1).sum() }.let { facs ->
        facs.filter { it.first == (facs.getOrNull(it.second)?.second ?: false) && it.first != it.second }
    }.map { it.first }

    override fun realProblem(): String = buildString {
        append("Find the sum of all the amicable numbers under 10000: ")
        val result = amicableNumbersBelow(10000)
        append(result.sum())
    }
}

fun main() {
    println(Problem21.solve())
}