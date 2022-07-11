package puzzle

import euler.timed
import java.io.File
import kotlin.random.Random

fun main(): Unit = timed {
    val input = File("app/src/main/resources/sentences.txt").readLines()

    // SubstitutionCipher(input.random(), alphabet).also { println(it) }
    SubstitutionCipher("i have written this sentence in a code made up of emojis to hide the information from those not willing to decode it", produce).also { println(it) }
    // SubstitutionCipher("i am hoping you solved this manually instead of using some online tool but a solution is a solution and the answer is diphthong", produce).also { println(it) }
    // SubstitutionCipher("i used to be a bookkeeper before i was exposed to antibookkeeping rhetoric", produce).also { println(it) }
    // SubstitutionCipher("I ate a sock because people on the Internet told me to", produce).also { println(it) }
    // SubstitutionCipher("I ate some sweets before I called a cab", handEmojis).also { println(it) }
    // SubstitutionCipher("this is a test; i am wondering if this is easy to decode", produce).also { println(it) }
}


class SubstitutionCipher(
    private val message: String,
    private val ciphertextAlphabet: List<String> = handEmojis,
) {
    fun <E> MutableList<E>.removeRandom(random: Random = Random.Default) =
        if (indices.last > 0) removeAt(random.nextInt(indices.last))
        else single()

    private val encoder = run {
        val availableGlyphs = ciphertextAlphabet.toMutableList()
        alphabet.associateWith { availableGlyphs.removeRandom() }.toSortedMap()
    }

    private fun List<String>.encode() = map { word ->
        word.map {
            requireNotNull(encoder[it.toString()]) {
                "encountered unexpected character in input: $it"
            }
        }.joinToString(separator = "")
    }

    private val words = message.trim().lowercase()
        .filterNot { it in listOf('’', ',', '\'', ';') }
        .split(' ')
    private val encodedWords = words.encode().joinToString(" ")
    private val decoder = encoder.values.joinToString("")
    private val library = encoder.values.shuffled().joinToString("\n")

    private val occurrences = with(words.flatMap { it.toList() }) {
        distinct().associateWith { char -> count { it == char } }.toList()
            .sortedByDescending { it.second }
            .map { (char, occurrences) -> requireNotNull(encoder[char.toString()]) to occurrences } // most occurrences first
    }

    private val digraphs = words.flatMap { word -> word.windowed(2) }
    private val repeatingDigraphs = digraphs.filter { it.first() == it.last() }.distinct()
    private val commonDigraphs = digraphs.associateWith { digraph ->
        digraphs.count { digraph == it }
    }.filter { it.value > 1 }.keys.toList()

    private val analyzerResult =
        AnalyzerResult(
            occurrences = occurrences,
            singleLetterWords = words.filter { it.length == 1 }.distinct().encode(),
            twoLetterWords = words.filter { it.length == 2 }.distinct().encode(),
            threeLetterWords = words.filter { it.length == 3 }.distinct().encode(),
            repeatingDigraphs = repeatingDigraphs.encode(),
            commonDigraphs = commonDigraphs.encode(),
        )


    data class AnalyzerResult(
        val occurrences: List<Pair<String, Int>> = emptyList(),
        val singleLetterWords: List<String> = emptyList(),
        val twoLetterWords: List<String> = emptyList(),
        val threeLetterWords: List<String> = emptyList(),
        val repeatingDigraphs: List<String> = emptyList(),
        val commonDigraphs: List<String> = emptyList(),
    ) {
        override fun toString() = buildString {
            val lines = listOf(
                "occurrences: $occurrences",
                "singleLetterWords: $singleLetterWords",
                "twoLetterWords: $twoLetterWords",
                "threeLetterWords: $threeLetterWords",
                "repeatingDigraphs: $repeatingDigraphs",
                "commonDigraphs: $commonDigraphs",
            )
            val rule = "+" + "-".repeat(lines.maxOf { it.length }) + "+"
            appendLine(rule)
            lines.forEach { appendLine("| $it") }
            appendLine(rule)
        }
    }

    override fun toString(): String = buildString {
        appendLine("words: ${words.joinToString(" ")}")
        appendLine("decoder: $decoder")
        appendLine("encodedWords:\n$encodedWords")
        appendLine("library:\n$library")
        append(analyzerResult)
    }
}