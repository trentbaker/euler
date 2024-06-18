package euler.problems

import euler.BigInteger
import euler.EulerProblem


object Problem29 : EulerProblem() {
    override val name = "Distinct Powers"

    private fun setOfPowers(aRange: IntRange, bRange: IntRange) = aRange.flatMap { a ->
        bRange.map { b ->
            BigInteger(a).pow(b)
        }
    }.toSortedSet()

    override fun exampleProblem(): String = buildString {
        append("Find the distinct terms of a^b where (2 <= a <= 5) and (2 <= b <= 5): ")
        val powers = setOfPowers(
            aRange = 2..5,
            bRange = 2..5
        )
        append(powers)
    }

    override fun realProblem(): String = buildString {
        append("How manny distinct terms of a^b where (2 <= a <= 100) and (2 <= b <= 100): ")
        val powers = setOfPowers(
            aRange = 2..100,
            bRange = 2..100
        )
        append(powers.count())
    }
}

fun main() {
    println(Problem29.solve())
}