package euler.problems

import euler.EulerProblem
import euler.problems.Problem191.enumerateStrings
import java.io.File

object Problem79 : EulerProblem() {
    override val name = "Passcode Derivation"

    private val KEYLOG_TXT = File("app/src/main/resources/0079_keylog.txt").readLines()

    // each of these logins is basically a regex 319 -> .*3.*1.*9.*
    private fun containsEachInOrder(input: String): Regex =
        Regex(input.toList().joinToString(separator = "", prefix = "\\d*") { "$it\\d*" })

    private val alphabet = KEYLOG_TXT.joinToString("").toSortedSet().joinToString("")

    override fun realProblem(): String = buildString {
        append("Determine the shortest possible passcode given keylog.txt: ")
        val possibleCodes = enumerateStrings(alphabet, 8)
            .filter {
                KEYLOG_TXT.all { keylog -> it.matches(containsEachInOrder(keylog)) }
            }

        append(possibleCodes.toList())
    }
}

fun main() {
    println(Problem79.solve())
}