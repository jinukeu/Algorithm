import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.contracts.contract
import kotlin.math.max

private val br = BufferedReader(InputStreamReader(System.`in`))

// 상하, 좌우
private val dirs = listOf(listOf(-1, 0), listOf(-1, -1), listOf(0, -1))
private var visited = mutableListOf<MutableList<Boolean>>()

fun main(args: Array<String>) {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    var graph = mutableListOf<MutableList<Int>>()
    repeat(n) {
        graph.add(br.readLine().split(" ").map { it.toInt() }.toMutableList())
    }

    for(i in 0 until n)
        for(j in 0 until  m) {
            var newMax = graph[i][j]
            var ma = 0
            dirs.forEach { dir ->
                val newI = i + dir[0]
                val newJ = j + dir[1]
                if(!(newI < 0 || newI > n - 1 || newJ < 0 || newJ > m - 1))
                    ma = max(graph[newI][newJ], ma)
            }
            graph[i][j] = newMax + ma
        }

    println(graph[n - 1][m - 1])
}