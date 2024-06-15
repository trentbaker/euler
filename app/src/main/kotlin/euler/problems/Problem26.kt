package euler.problems

import euler.problems.Problem26.decimalString
import euler.problems.Problem26.detectCycle
import java.math.BigDecimal
import java.math.BigDecimal.ROUND_DOWN
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

/**
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part
 */
const val MAX_LENGTH = 2000  // trial and error
suspend fun main() {
    measureTimeMillis {
        val cycles = runBlocking {
            (1 until 10).map { denominator ->
                async(start = CoroutineStart.LAZY) {
                    val decimalString = decimalString(denominator, MAX_LENGTH)
                    detectCycle(decimalString)?.let { cycle ->
                        Problem26.CycleResult(
                            denominator = denominator,
                            tailLength = decimalString.length,
                            cycle = cycle,
                            cycleLength = cycle.length
                        )
                    }.also {
                        println("finished d = $denominator")
                    }
                }
            }.awaitAll()
        }
        val answer = cycles.maxByOrNull { it?.cycle?.length ?: -1 }
        println(answer)
    }.also { println("took ${it}ms") }
}

object Problem26 {
    data class CycleResult(
        val denominator: Int,
        val tailLength: Int,
        val cycleLength: Int,
        val cycle: String,
    )

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

    fun decimalString(denominator: Int, maxLength: Int): String =
        BigDecimal.ONE.divide(BigDecimal(denominator), maxLength, ROUND_DOWN).stripTrailingZeros()
            .toString().drop(2)
}

