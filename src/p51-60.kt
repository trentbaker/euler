fun main() {
    println("PE54. How many hands does Player 1 win in the set of poker hands given: " +
            POKER_HANDS.importAsHands().first().let {
                println("${it.first}: ${it.first.score()}")
                println("${it.second}: ${it.second.score()}")
            }
    )
}