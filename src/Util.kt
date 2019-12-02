fun timed(fn: () -> Any): Long = System.currentTimeMillis().also { start ->
	fn().also { println("took ${System.currentTimeMillis() - start}ms") }
}
