import java.io.BufferedReader
import java.io.InputStreamReader

private val br = BufferedReader(InputStreamReader(System.`in`))
private lateinit var graph: Array<Array<Int>>
private lateinit var visited: Array<Array<Boolean>>
var n = 0
var ans = "Hing"

fun main(args: Array<String>) {
    n = br.readLine().toInt()
    graph = Array(n) { Array(n) { 0 } }
    visited = Array(n) { Array(n) { false } }

    repeat(n) { index ->
        graph[index] = br.readLine().split(" ").map { it.toInt() }.toTypedArray()
    }

    dfs(0, 0)
    print(ans)
}

fun dfs(x: Int, y: Int) {
    visited[y][x] = true

    if(x == n - 1 && y == n - 1) {
        ans = "HaruHaru"
        return
    }

    val diff = graph[y][x]
    if(y + diff < n && !visited[y + diff][x]) dfs(x, y + diff)
    if(x + diff < n && !visited[y][x + diff]) dfs(x + diff, y)
}