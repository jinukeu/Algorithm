import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = mutableListOf<List<Int>>()

    repeat(n) {
        graph.add(br.readLine().split(" ").map { it.toInt() })
    }

    val prefixSum = MutableList(n + 1) { MutableList(m + 1) { 0 } }

    for(i in 1 .. n) {
        var sumValue = 0
        for (j in 1..m) {
            sumValue += graph[i - 1][j - 1]
            prefixSum[i][j] = sumValue
        }
    }
    
    repeat(br.readLine().toInt()) {
        val (i, j, x, y) = br.readLine().split(" ").map { it.toInt() }
        var ans = 0
        for(now in i .. x) {
            ans += prefixSum[now][y] - prefixSum[now][j - 1]
        }
        println(ans)
    }
}