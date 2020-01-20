package problems

import java.io.File

class Sudoku(private val board: List<List<Char>>) {

    init {
        require(board.size == 9 && board.all { it.size == 9 })
    }

    constructor(input: File) : this(input.readLines()
            .filter { it.length > 1 } // trim trailing newlines
            .map { row ->
                row.mapNotNull {
                    when (it) {
                        ' ' -> null
                        '0' -> ' '
                        else -> it
                    }
                }
            }
    )

    fun solve(input: Sudoku = this, trace: Boolean = false): Boolean {
        if (input.isSolved) {
            if (!trace) println(input)
            return true
        }
        input.mostConstrainedDomain.forEach { char ->
            input.setMostConstrained(char).let {
                if (trace) {
                    println("${input.mostConstrainedDomain}: ${input.mostConstrainedCell.let { it.second to it.first }} -> $char")
                    println(it)
                }
                if (it.isValid && solve(it, trace)) return true
            }
        }
        return false
    }

    override fun toString(): String = board.map {
        (listOf('|') + it.slice(0..2) +
                '|' + it.slice(3..5) +
                '|' + it.slice(6..8) +
                '|').joinToString(" ")
    }.let {
        val rule = "+---+---+---+".toList().joinToString(" ")
        (listOf(rule) + it.slice(0..2) +
                rule + it.slice(3..5) +
                rule + it.slice(6..8) +
                rule).joinToString("\n")
    }

    private val isValid: Boolean = listOf(
            board.all { row ->
                row.all { char -> char == ' ' || row.count { it == char } == 1 }
            },
            board.transposed().all { column ->
                column.all { char -> char == ' ' || column.count { it == char } == 1 }
            },
            listOf(1 to 1, 4 to 1, 7 to 1, 1 to 4, 4 to 4, 7 to 4, 1 to 7, 4 to 7, 7 to 7)
                    .all { it.isValidSquare() }
    ).all { it }

    private fun Pair<Int, Int>.isValidSquare(): Boolean = neighbors().let { grid ->
        grid.all { char -> char == ' ' || grid.count { it == char } == 1 }
    }

    private val isSolved: Boolean = board.all { it.none { it == ' ' } } && isValid

    private val constraintMap: List<List<Int>> = board.mapIndexed { i, row ->
        row.mapIndexed { j, char ->
            if (char != ' ') 0 else listOf(board[i].count { it != ' ' },
                    board.map { it[j] }.count { it != ' ' },
                    (i to j).nearestCenter().neighbors().count { it != ' ' }
            ).sum()
        }
    }

    private val mostConstrainedCell: Pair<Int, Int> = constraintMap.mapIndexedNotNull() { i, row ->
        row.mapIndexed { j, constraint -> (i to j) to constraint }.maxBy { it.second }
    }.maxBy { it.second }?.first ?: throw Exception("no max?")

    private val mostConstrainedDomain: List<Char> = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
            .filter { !board[mostConstrainedCell.first].contains(it) }
            .filter { !board.map { it[mostConstrainedCell.second] }.contains(it) }
            .filter { !mostConstrainedCell.nearestCenter().neighbors().contains(it) }

    private fun setMostConstrained(input: Char): Sudoku = Sudoku(board
            .map { it.toMutableList() }
            .also { it[mostConstrainedCell.first][mostConstrainedCell.second] = input }
            .map { it.toList() }
    )

    private fun Pair<Int, Int>.nearestCenter(): Pair<Int, Int> = when {
        second <= 2 -> 1
        second <= 5 -> 4
        else -> 7
    } to when {
        first <= 2 -> 1
        first <= 5 -> 4
        else -> 7
    }

    private fun <T> List<List<T>>.transposed(): List<List<T>> {
        require(map { it.size }.distinct().size == 1)
        val out: MutableList<MutableList<T>> = mutableListOf()
        for (i in this.first().indices) {
            out.add(mutableListOf())
            for (j in indices) {
                out.last().add(get(j)[i])
            }
        }
        return out.map { it.toList() }.toList()
    }

    private fun Pair<Int, Int>.neighbors(): List<Char> = listOf(
            first - 1 to second - 1,
            first - 1 to second,
            first - 1 to second + 1,
            first to second - 1,
            first to second,
            first to second + 1,
            first + 1 to second - 1,
            first + 1 to second,
            first + 1 to second + 1
    ).map { board[it.second][it.first] }
}
