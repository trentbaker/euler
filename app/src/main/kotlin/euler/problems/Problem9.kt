package euler.problems

import euler.BigInteger
import euler.EulerProblem
import java.math.BigInteger

object Problem9 : EulerProblem() {
    override val name = "Special Pythagorean Triplet"
    // this only finds pythagorean triples that could possibly sum to 1000 because that is our goal
    private val pythagoreanTriples = generateSequence(Triple(0, 0, 0)) { (a, b, c) ->
        when {
            c < 997 -> Triple(a, b, c + 1)
            b < 997 -> Triple(a, b + 1, 0)
            a < 997 -> Triple(a + 1, 0, 0)
            else -> null
        }
    }.filter { (a, b, c) -> b in (a + 1)..<c }
        .filter { (a, b, c) ->
            BigInteger(a).pow(2) + BigInteger(b).pow(2) == BigInteger(c).pow(2)
        }

    override fun exampleProblem(): String = buildString {
        append("There exists exactly one Pythagorean triplet for which a + b + c = 56. Find the Product abc: ")
        val result = pythagoreanTriples
            .single { (a, b, c) -> a + b + c == 56 }
            .let { (a, b, c) -> a * b * c }
        append(result)
    }

    override fun realProblem(): String = buildString {
        append("There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the Product abc: ")
        val result = pythagoreanTriples
            .single { (a, b, c) -> a + b + c == 1000 }
            .let { (a, b, c) -> a * b * c }
        append(result)
    }
}

fun main() {
    println(Problem9.solve())
}