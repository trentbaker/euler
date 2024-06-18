package euler.problems

import euler.timed
import java.io.File

/**
 * A common security method used for online banking is to ask the user for three random characters from a passcode. For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.
 *
 * The text file, keylog.txt, contains fifty successful login attempts.
 *
 * Given that the three characters are always asked for in order, analyse the file so as to determine the shortest possible secret passcode of unknown length.
 */
object Problem79 {
    // [319, 680, 180, 690, 129, ...]
    private val KEYLOG_TXT = File("app/src/main/resources/0079_keylog.txt").readLines()

    // each of these three logins is basically a regex 319 -> .*3.*1.*9.*
    private fun containsEachInOrder(input: String): Regex =
        Regex(input.toList().joinToString(separator = "", prefix = "\\d*") { "$it\\d*" })

    private val alphabet = KEYLOG_TXT.joinToString("").toSortedSet()

    // this is dirty, but regex sre fast enough
    val solution = timed {
        Problem191.enumerateStrings(alphabet.toList(), 8)
            .filter { KEYLOG_TXT.all { keylog -> it.matches(containsEachInOrder(keylog)) } }.toList()
    }
}

fun main() {
    timed {
        println(Problem79.solution)
    }
}