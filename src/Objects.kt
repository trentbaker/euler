class BinaryNode(var value: Int): Comparable<BinaryNode> {
    var l: BinaryNode? = null
    var r: BinaryNode? = null

    override fun toString(): String = value.toString()
    override fun compareTo(other: BinaryNode) = value

    fun eatLargestLivingChild() {
        value += listOfNotNull(l, r).maxBy { it.value }?.value ?: 0
    }
}

class Card(val code: String) {
    private val ranks = listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
    private val suits = listOf('S', 'H', 'C', 'D')
    init {
        require(code.length == 2)
        require(ranks.contains(code.first()))
        require(suits.contains(code.last()))
    }

    private val rank = ranks.first { it == code.first()}
    private val suit = suits.first { it == code.last()}

    override fun toString() = code
}