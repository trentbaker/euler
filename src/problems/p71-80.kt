package problems

fun main() {
    println("PE74. How many digit factorial chains, with a starting number below one million, contain exactly sixty non-repeating terms: " +
            // IntRange(1, 1000000).map { main.digitFactorialChainUntilNotUnique(it).size }.count { it == 60 } // took 40.923s
            "402"
    )
}