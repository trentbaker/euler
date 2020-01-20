import objects.BinaryNode

fun <T> timed(fn: () -> T): T = System.currentTimeMillis().let { start ->
	fn().also { println("took ${System.currentTimeMillis() - start}ms") }
}

typealias BinaryTree = List<List<BinaryNode>>

fun String.swap(a: Int, b: Int) = this.let { original ->
	toMutableList().apply {
		set(a, original[b])
		set(b, original[a])
	}.joinToString("")
}