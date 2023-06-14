import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.system.exitProcess

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (h, w) = br.readLine().split(" ").map { it.toInt() }
    val graph = mutableListOf<List<Shape>>()

    repeat(h) {
        graph.add(br.readLine().split(" ").map { Shape(id = 0, size = 0, value = it.toInt()) })
    }

    val visited = Array(h) { Array(w) { false } }

    val dirs = listOf(listOf(0, 1), listOf(1, 0), listOf(-1, 0), listOf(0, -1))

    var id = 0

    for(i in 0 until h)
        for(j in 0 until w) {
            if(graph[i][j].value == 0 || visited[i][j]) continue

            id += 1
            val queue: Queue<Node> = LinkedList()
            var answer = 1
            queue.add(Node(j, i))
            visited[i][j] = true

            val nodesNeedUpdate = mutableListOf<Node>(Node(j, i))

            while (queue.isNotEmpty()) {
                val (x, y) = queue.poll()

                for((dx, dy) in dirs) {
                    val nx = x + dx
                    val ny = y + dy

                    if(nx in 0 until w && ny in 0 until h) {
                        if(visited[ny][nx] || graph[ny][nx].value == 0) continue
                        answer += 1
                        visited[ny][nx] = true
                        queue.add(Node(nx, ny))
                        nodesNeedUpdate.add(Node(nx, ny))
                    }
                }
            }

            nodesNeedUpdate.forEach { (x, y) ->
                graph[y][x].apply {
                    this.id = id
                    this.size = answer
                }
            }
        }

//    graph.forEach { nodes ->
//        println(nodes)
//    }

    var answer = 0

    for(y in 0 until h)
        for(x in 0 until w) {
            if(graph[y][x].value == 1) continue

            val temp = mutableListOf<Shape>()
            for((dx, dy) in dirs) {
                val (nx, ny) = (x + dx to y + dy)
                if(nx in 0 until w && ny in 0 until h) {
                    temp.add(graph[ny][nx])
                }
            }

            answer = max(answer, (temp.distinctBy { it.id }.sumOf { it.size }))
        }

    println(answer + 1)

}

data class Shape(
    var id: Int,
    var size: Int,
    val value: Int
)

data class Node(
    val x: Int,
    val y: Int
)