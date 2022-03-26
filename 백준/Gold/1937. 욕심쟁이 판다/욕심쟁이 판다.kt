import kotlin.math.max

val dir = arrayOf(arrayOf(1, 0), arrayOf(0, 1), arrayOf(-1, 0), arrayOf(0, -1))
var n = 0
lateinit var dp: Array<Array<Int>>
lateinit var graph: ArrayList<List<Int>>
fun main(args: Array<String>) {
    n = readln().toInt()
    graph = ArrayList()
    dp = Array(n) {Array(n) { 0 } }

    repeat(n) {
        graph.add(readln().split(" ").map {it.toInt()})
    }

    for(i in 0 until n) {
        for(j in 0 until n) {
            if(dp[i][j] == 0)
                dfs(graph[i][j], i, j)
        }
    }

    println(dp.maxOf { it.maxOf { d -> d } })
}

fun dfs(prevBamboo: Int, i: Int, j: Int): Int {
    if(dp[i][j] != 0)
        return dp[i][j]
    dp[i][j] = 1
    dir.forEach {
        val ni = it[0] + i
        val nj = it[1] + j
        if(ni in 0 until n && nj in 0 until n) {
            val nextBamboo = graph[ni][nj]
            if(nextBamboo > prevBamboo)
                dp[i][j] = max(dp[i][j], dfs(nextBamboo, ni, nj) + 1)
        }
    }
    return dp[i][j]
}