import kotlin.math.max
import kotlin.system.exitProcess

val dirs = arrayOf(arrayOf(1, 0), arrayOf(0, 1), arrayOf(-1, 0), arrayOf(0, -1))
lateinit var dp: Array<Array<Int>>
var graph = ArrayList<List<String>>()
fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map {it.toInt()}
    dp = Array(n) {Array(m) {1} }
    repeat(n) {
        graph.add(readln().split("").subList(1, m + 1))
    }

    dfs(0, 0, n, m, arrayListOf())

    println(dp.maxOf { it.maxOf { d -> d } })
}

fun dfs(i: Int, j: Int, n: Int, m: Int, stk: ArrayList<Pair<Int, Int>>): Int {
    if(dp[i][j] != 1)
        return dp[i][j]
    if(graph[i][j] == "H")
        return 0
    for((di, dj) in dirs) {
        val ni = i + di * graph[i][j].toInt()
        val nj = j + dj * graph[i][j].toInt()
        if(ni in 0 until n && nj in 0 until m){
            if(Pair(ni, nj) in stk) {
                print(-1)
                exitProcess(0)
            }
            stk.add(Pair(ni, nj))
            dp[i][j] = max(dp[i][j], dfs(ni, nj, n, m, stk) + 1)
            stk.removeLast()
        }
    }
    return dp[i][j]
}