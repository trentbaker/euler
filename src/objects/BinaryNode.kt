package objects

class BinaryNode(var value: Int) : Comparable<BinaryNode> {
    var l: BinaryNode? = null
    var r: BinaryNode? = null

    override fun toString(): String = value.toString()
    override fun compareTo(other: BinaryNode) = value

    fun eatLargestLivingChild() {
        value += listOfNotNull(l, r).maxBy { it.value }?.value ?: 0
    }
}
