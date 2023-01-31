import java.io.BufferedReader
import java.io.InputStreamReader


private val br = BufferedReader(InputStreamReader(System.`in`))
private val dirs = listOf(
    listOf(0, 1),
    listOf(1, 0),
    listOf(-1, 0),
    listOf(0, -1)
)

fun main(args: Array<String>) {
    val (width, height) = br.readLine().split(" ").map { it.toInt() }
    val graph = Array(height) { intArrayOf() }
    val visited = Array(height) { Array(width) { false } }

    repeat(height) { idx ->
        graph[idx] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val queue = ArrayDeque<Node>()
    var day = -1

    // 익은 토마토의 위치를 queue에 저장한다.
    for(h in 0 until height)
        for(w in 0 until width) {
            if(graph[h][w] == 1) {
                queue.add(Node(h, w))
                visited[h][w] = true
            }
        }

    while (queue.isNotEmpty()) {

        day++

        repeat (queue.size) {
            val (currentI, currentJ) = queue.removeFirst()
            for((di, dj) in dirs) {
                val (newI, newJ) = listOf(currentI + di, currentJ + dj)
                if(
                    newI !in 0 until height ||
                    newJ !in 0 until width ||
                    graph[newI][newJ] == -1 ||
                    graph[newI][newJ] == 1 ||
                    visited[newI][newJ]
                ) continue

                graph[newI][newJ] = 1
                queue.add(Node(newI,newJ))
                visited[newI][newJ] = true
            }
        }
    }

    for(g in graph) {
        if(0 in g) day = -1
    }

    print(day)
}

data class Node(
    val i: Int,
    val j: Int
)