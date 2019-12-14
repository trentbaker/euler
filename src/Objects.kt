import java.lang.Exception

class BinaryNode(var value: Int): Comparable<BinaryNode> {
    var l: BinaryNode? = null
    var r: BinaryNode? = null

    override fun toString(): String = value.toString()
    override fun compareTo(other: BinaryNode) = value

    fun eatLargestLivingChild() {
        value += listOfNotNull(l, r).maxBy { it.value }?.value ?: 0
    }
}

class Ring<T>(private val values: List<T>, private val weight: Int = 1) {
    init {
        assert(values.isNotEmpty()) { throw Exception("must have values") }
        assert(weight > 0) { throw Exception("must have weight > 0") }
    }

    override fun toString() = values.toString()

    private var count = 0

    fun current() = values[count / weight]

    fun inc(i: Int = 1): Ring<T> {
        count += i
        if ((count / weight) > values.lastIndex) count %= values.size
        return this
    }
}

class Lexicon<T>(private val tumblers: List<Ring<T>>) {
    override fun toString() = current().toString()
    fun current() = tumblers.map { it.current() }
    private fun inc(i: Int) = Lexicon(tumblers.map { it.inc(i) })

    fun get(index: Int) = inc(index).current()
}
