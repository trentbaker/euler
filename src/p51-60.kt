fun main() {
	println("PE54. How many hands does Player 1 win in the set of poker hands given: " +
		POKER_HANDS.importAsHands().count { it.first > it.second }
	)
}
