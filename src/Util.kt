fun <T> timed(fn: () -> T): T = System.currentTimeMillis().let { start ->
	fn().also { println("took ${System.currentTimeMillis() - start}ms") }
}

typealias BinaryTree = List<List<BinaryNode>>
