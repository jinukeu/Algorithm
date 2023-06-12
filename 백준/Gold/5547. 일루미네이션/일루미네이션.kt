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
    val (w, h) = br.readLine().split(" ").map { it.toInt() }

    val evenDir = listOf(listOf(-1, 0), listOf(-1, +1), listOf(0, + 1), listOf(1, 0), listOf(0, -1), listOf(-1,-1))
    val oddDir = listOf(listOf(-1, 0), listOf(0, +1), listOf(1, + 1), listOf(1, 0), listOf(1, -1), listOf(0,-1))
    val graph = mutableListOf<List<Int>>()

    graph.add(List(w + 2) { 0 })
    repeat(h) {
        graph.add(listOf(0) +  br.readLine().split(" ").map { it.toInt() } + listOf(0))
    }
    graph.add(List(w + 2) { 0 })

    val visited = Array(h + 2) { Array(w + 2) { false } }
    val queue: Queue<Node> = LinkedList()

    var answer = 0
    queue.add(Node(0, 0))
    visited[0][0] = true

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()

        val dirs = if(y % 2 == 0) evenDir else oddDir
        for((dx, dy) in dirs) {
            val nx = x + dx
            val ny = y + dy

            if(nx in 0 until w + 2 && ny in 0 until h + 2) {
                if(visited[ny][nx]) continue
                if(graph[ny][nx] == 1) {
                    answer += 1
                    continue
                }
                visited[ny][nx] = true
                queue.add(Node(nx, ny))
            }

        }
    }

    println(answer)

}

data class Node(
    val x: Int,
    val y: Int
)