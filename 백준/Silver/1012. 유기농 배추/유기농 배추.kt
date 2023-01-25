import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Deque


private val br = BufferedReader(InputStreamReader(System.`in`))
private val dirs = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1))

var realAnswer = ""

fun main(args: Array<String>) {
    repeat(br.readLine().toInt()) {
        val (width, height, count) = br.readLine().split(" ").map { it.toInt() }
        getMinEarthwormCount(width, height, count)
    }

    print(realAnswer)
}

fun getMinEarthwormCount(width: Int, height: Int, count: Int) {
    val graph = Array(height) { Array(width) { 0 } }
    val visited = Array(height) { Array(width) { false } }
    var answer = 0

    repeat(count) {
        val (x, y) = br.readLine().split(" ").map { it.toInt() }
        graph[y][x] = 1
    }

    for (i in 0 until height) {
        for (j in 0 until width) {
            if (visited[i][j].not() && graph[i][j] == 1) {
                bfs(graph, visited, i, j, width, height)
                answer += 1
            }
        }
    }

    realAnswer += "$answer\n"
}

// 근처의 1을 모두 방문 처리한다.
fun bfs(graph: Array<Array<Int>>, visited: Array<Array<Boolean>>, i: Int, j: Int, width: Int, height: Int) {
    val queue = ArrayDeque<Node>()
    queue.add(Node(i, j))
    visited[i][j] = true
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        for ((di, dj) in dirs) {
            val (newI, newJ) = listOf(node.i + di, node.j + dj)

            if (
                newI in 0 until height &&
                newJ in 0 until width &&
                visited[newI][newJ].not() &&
                graph[newI][newJ] == 1
            ) {
                visited[newI][newJ] = true
                queue.add(Node(newI, newJ))
            }

        }
    }
}

data class Node(
    val i: Int,
    val j: Int
)