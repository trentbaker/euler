package euler.problems

import euler.NAMES_FILE
import euler.amicableNumbersBelow
import euler.bigFibonacciSequence
import euler.calculateNameScores
import euler.lexicographicSequence
import euler.over

fun main() {
    println(
        "PE21. Evaluate the sum of all the amicable numbers under 10000: " +
                amicableNumbersBelow(10000).sum()
    )
    println("PE22. What is the total of all the name scores in names.txt: " +
            NAMES_FILE.calculateNameScores().sumOf { it.second }
    )
    println(
        "PE23. Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers: " +
                // IntRange(0, 28123).toList().cannotSumFromAbundant().sum() // took 88.247s
                4179871
    )
    println(
        "PE24. What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9: " +
                lexicographicSequence("0123456789").elementAt(999999).joinToString("")
    )
    println("PE25. What is the index of the first term in the Fibonacci sequence to contain 1000 digits: " +
            bigFibonacciSequence().mapIndexed { index, it -> it to index }
                .filter { it.first.toString().length == 1000 }
                .first().second
    )
    println(
        "Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part: "
    )
    // (1 until 10).map { (1 over it) }.forEach { println("$it: ${it.cycle}") }
    (1 over 7).let { println("$it: ${it.cycle}") }

}