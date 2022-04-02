import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val n = readln().toInt()
    val graph = mutableListOf<MutableList<Int>>()
    val used = Array(n) { BooleanArray(n) { true } }
    repeat(n) {
        graph.add(readln().split(" ").map { it.toInt() }.toMutableList())
    }

    for (k in 0 until n)
        for (i in 0 until n)
            for (j in 0 until n) {
                if (i == k || i == j || j == k) continue
                if (graph[i][j] > graph[i][k] + graph[k][j]) {
                    println(-1)
                    exitProcess(0)
                }
                if (graph[i][j] == (graph[i][k] + graph[k][j]))
                    used[i][j] = false
            }

    var ans = 0
    for (i in 0 until n)
        for (j in 0 until n) {
            if (used[i][j])
                ans += graph[i][j]
        }

    println(ans / 2)
}