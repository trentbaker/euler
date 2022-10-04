package euler.problems

import java.io.File

object Problem42 {
    // The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1);
    // so the first ten triangle numbers are:
    //
    // 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
    //
    // By converting each letter in a word to a number corresponding to its alphabetical position and
    // adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10.
    // If the word value is a triangle number then we shall call the word a triangle word.

    // Using words.txt a text file containing nearly two-thousand common English words, how many are triangle words?

    val words = File("app/src/main/resources/p042_words.txt").readLines()

    val triangleNumbers = generateSequence(1) { it + 1 }
        .map { n -> (0.5 * n * (n + 1)).toInt() }

    fun wordValue(word: String) = word.map { it.code - 64 }.sum()
}

fun main() {
    val words = File("app/src/main/resources/p042_words.txt")
        .readText()
        .split(',')
        .map { it.drop(1).dropLast(1) }

    val wordValues = words.associateWith { Problem42.wordValue(it) }
    val maxWordValue = wordValues.maxOf { (_, wordValue) -> wordValue }

    val relevantTriangleNumbers = Problem42.triangleNumbers.takeWhile { it < maxWordValue }.toList()

    val results = wordValues.map { (word, wordValue) ->
        Triple(word, wordValue, wordValue in relevantTriangleNumbers)
    }

    buildString {
        results.forEach {(word, _, isTriangle)->
            if (isTriangle) append("∆\t")
            else append("\t")
            appendLine(word)
        }
        append("number of triangle words: ")
        appendLine(results.count { it.third })
    }.also { print(it) }
}