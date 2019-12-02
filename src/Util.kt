fun timed(fn: () -> Any) = System.currentTimeMillis().also { start ->
	fn().also { println("took ${System.currentTimeMillis() - start}ms") }
}
