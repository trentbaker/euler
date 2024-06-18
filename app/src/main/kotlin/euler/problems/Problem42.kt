package euler.problems

import euler.EulerProblem
import java.io.File

object Problem42 : EulerProblem() {
    override val name = "Coded Triangle Numbers"

    private val WORDS_TXT = File("app/src/main/resources/p042_words.txt")
        .readText()
        .filter { it != '"' }
        .split(",")

    private val wordScores = WORDS_TXT.associateWith {
        it.sumOf { it.code - 64 }
    }
    private val maxWordScore = wordScores.maxOf { it.value }

    private val triangleNumbers = Problem12.triangleNumbers.takeWhile {
        it < maxWordScore
    }.toList()

    override fun realProblem(): String = buildString {
        append("How many of words.txt are triangle words: ")
        append(wordScores.count { it.value in triangleNumbers })
    }

}

fun main() {
    println(Problem42.solve())
}