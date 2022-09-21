import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import kotlin.system.exitProcess

private val br = BufferedReader(InputStreamReader(System.`in`))
private val dirs = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1))
private val visited = mutableListOf<MutableList<Boolean>>()

fun main(args: Array<String>) {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = mutableListOf<List<String>>()
    repeat(n) {
        graph.add(br.readLine().split("").subList(1, m + 1))
        visited.add(MutableList(m){false})
    }
    val queue: Queue<N> = LinkedList()
    for(j in 0 until m) {
        if(graph[0][j] == "0") {
            queue.add(N(0, j))
            visited[0][j] = true
        }
    }

    var ans = "NO"
    while (queue.isNotEmpty()) {
        val (i, j) = queue.poll()
        if(i == n - 1) {
            ans = "YES"
            break
        }
        for(index in dirs.indices) {
            val (di, dj) = dirs[index]
            val newI = i + di
            val newJ = j + dj
            if(newI < 0 || newI > n - 1 || newJ < 0 || newJ > m - 1) continue
            if(visited[newI][newJ]) continue
            if(graph[newI][newJ] == "1") continue
            visited[newI][newJ] = true

            queue.add(N(newI, newJ))
        }
    }

    println(ans)
}

data class N(
    val i: Int,
    val j: Int
)