package euler.problems

import euler.EulerProblem
import java.io.File

object Problem22 : EulerProblem() {
    override val name = "Names Scores"

    private fun calculateNameScores(names: Sequence<String>) = names.sorted().mapIndexed { index, s ->
        s to s.sumOf { it.code - 64 } * (index + 1)
    }

    private val NAMES_TXT = File("app/src/main/resources/0022_names.txt")
        .readText()
        .filter { it != '"' }
        .splitToSequence(",")

    override fun realProblem(): String = buildString {
        append("Find the total of all the name scores in the file: ")
        val totalScore = calculateNameScores(NAMES_TXT)
            .onEach { println(it) }
            .sumOf { it.second }
        append(totalScore)
    }
}

fun main() {
    println(Problem22.solve())
}