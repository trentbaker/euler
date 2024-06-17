package euler.problems

import euler.EulerProblem
import java.math.BigDecimal
import java.math.RoundingMode

object Problem26 : EulerProblem() {
    override val name = "Reciprocal Cycles"
    fun detectCycle(input: String): String? {
        val output = input.indices.mapNotNull { index ->
            val remainingCharacters = input.drop(index + 1)
            val currentCharacter = input[index]
            if (currentCharacter !in remainingCharacters) null
            else {
                val indexMap = remainingCharacters.mapIndexed { i, c -> i + index + 1 to c }.toMap()
                val nextOccurrences = indexMap.filterValues { it == currentCharacter }

                val possibleCycles = nextOccurrences.keys.map { input.slice(index until it) }
                val repeatingCycles = possibleCycles.filter { possibleCycle ->
                    val remainingAfterCycle = remainingCharacters.drop(possibleCycle.length - 1)

                    // bail early if we can't have proof the pattern repeats
                    if (possibleCycle.length > (input.length / 2)) return@filter false

                    val chunked = remainingAfterCycle.chunked(possibleCycle.length)
                    val remainderRepeats = with(chunked) {
                        when {
                            size > 1 -> limit(1) { it != possibleCycle } // allow last chunk to be truncated
                            size == 1 -> single() == possibleCycle // perfect match
                            else -> false
                        }
                    }
                    remainderRepeats
                }

                repeatingCycles.minByOrNull { it.length }
                    ?.ifBlank { currentCharacter.toString() }
            }
        }
        return output.maxByOrNull { it.length }
    }

    private fun <T> Collection<T>.limit(limit: Int, predicate: (T) -> Boolean): Boolean {
        var occurrences = 0
        forEach {
            if (occurrences > limit) return false
            if (predicate(it)) occurrences++
        }
        return occurrences <= limit
    }

    override fun exampleProblem() = buildString {
        append("Find the denominator between 1 and 9 that has the longest repeating cycle: ")
        val cycles = (1 until 10).asSequence().associateWith {
            val decimalString = BigDecimal.ONE.divide(BigDecimal(it), 2000, RoundingMode.DOWN)
                .stripTrailingZeros().toString()
            detectCycle(decimalString.drop(2))
        }

        val result = cycles.maxBy { it.value?.length ?: 0 }.key

        append(result)
    }

    override fun realProblem() = buildString {
        append("Find the denominator between 1 and 1000 that has the longest repeating cycle: ")
        val cycles = (1 until 1000).asSequence().associateWith {
            val decimalString = BigDecimal.ONE.divide(BigDecimal(it), 2000, RoundingMode.DOWN)
                .stripTrailingZeros().toString()
            detectCycle(decimalString.drop(2))
        }

        val result = cycles.maxBy { it.value?.length ?: 0 }.key

        append(result)
    }

}

fun main() {
    println(Problem26.solve())
}

