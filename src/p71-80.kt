fun main() {
    println("PE74. How many digit factorial chains, with a starting number below one million, contain exactly sixty non-repeating terms: " +
            IntRange(1, 1000000).map { digitFactorialChainUntilNotUniqueLength(it) }.count { it == 60 }
    )
}