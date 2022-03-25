import kotlin.math.*

fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        acm()
    }
}

fun acm() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val buildTime = listOf(0) + readln().split(" ").map { it.toInt() }
    val entry = Array(n + 1) { 0 }
    val graph = Array(n + 1) { arrayListOf<Int>() }
    repeat(k) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        graph[x].add(y)
        entry[y]++
    }

    val w = readln().toInt()
    val dp = buildTime.toMutableList()

    val stack = mutableListOf<Int>()

    for(i in 1..n) {
        if(entry[i] == 0)
            stack.add(i)
    }

    while(stack.isNotEmpty()) {
        val now = stack.removeLast()
        graph[now].forEach {
            dp[it] = max(dp[it], buildTime[it] + dp[now])
            if(--entry[it] == 0)
                stack.add(it)
        }
    }

    println(dp[w])
}