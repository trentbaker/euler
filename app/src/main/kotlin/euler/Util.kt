package euler

import euler.objects.BinaryNode
import euler.objects.Sudoku
import java.io.File

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

fun File.importSudokus() = readLines().chunked(10).map {
	val importedBoard = it.drop(1).map { it.mapNotNull {
		when (it) {
			' ' -> null
			'0' -> ' '
			else -> it
		}
	} }
	Sudoku(importedBoard, it.first())
}