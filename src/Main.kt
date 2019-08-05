fun main() {
//    Find the sum of all the multiples of 3 or 5 below 1000.
    println("PE1. Find the sum of all the multiples of 3 or 5 below 1000:" +
            "${(0 until 1000).toList().filter { it % 3 == 0 || it % 5 == 0 }.sum()}")
    
    println("PE2. By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms:" +
            "${fibonacciUntil(4000000).filter { it % 2 == 0 }.sum()}")
}

fun fibonacciUntil(max: Int) = mutableListOf(1, 1)
    .also { while (it.last() < max) it.add(it.takeLast(2).sum()) }