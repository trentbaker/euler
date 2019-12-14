fun main() {
    println("PE21. Evaluate the sum of all the amicable numbers under 10000: " +
            amicableNumbersBelow(10000).sum()
    )
    println("PE22. What is the total of all the name scores in names.txt: " +
            NAMES_FILE.calculateNameScores().sumBy { it.second }
    )
    println("PE23. Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers: " +
            // IntRange(0, 28123).toList().cannotSumFromAbundant().sum() // took 88.247s
            4179871
    )
    println("PE25. What is the index of the first term in the Fibonacci sequence to contain 1000 digits: " +
        bigFibonacciSequence().mapIndexed { index, it ->  it to index }.filter { it.first.toString().length == 1000 }.first().second
    )
}