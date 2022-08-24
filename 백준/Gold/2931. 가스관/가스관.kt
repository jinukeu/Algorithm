import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.contracts.contract

private val br = BufferedReader(InputStreamReader(System.`in`))

val top = listOf("|", "+", "1", "4")
val bottom = listOf("|", "+", "2", "3")
val right = listOf("-", "+", "3", "4")
val left = listOf("-", "+", "1", "2")

fun main(args: Array<String>) {
    val (r, c) = br.readLine().split(" ").map { it.toInt() }
    val graph = mutableListOf<List<String>>()
    repeat(r) {
        graph.add(br.readLine().split("").filter { it != "" })
    }

    for(y in 0 until r)
        for(x in 0 until c) {
            if(graph[y][x] != ".") continue

            val pipeCandidate = mutableListOf<String>()

            check(x, y, c, r, graph, pipeCandidate, top, 0, -1)
            check(x, y, c, r, graph, pipeCandidate, bottom, 0, 1)
            check(x, y, c, r, graph, pipeCandidate, left, -1, 0)
            check(x, y, c, r, graph, pipeCandidate, right, 1, 0)


            var ans = ""
                pipeCandidate.forEach { pipe ->
                if(pipeCandidate.count { it == pipe } == 2 && pipe != "+") ans = pipe
            }


            if(pipeCandidate.size in 8..15) {
                println("${y + 1} ${x + 1} $ans")
            } else if(pipeCandidate.size == 16) {
                println("${y + 1} ${x + 1} +")
            }
        }
}

private fun check(
    x: Int,
    y: Int,
    c: Int,
    r: Int,
    graph: MutableList<List<String>>,
    pipeCandidate: MutableList<String>,
    direction: List<String>,
    dx: Int,
    dy: Int
) {
    val (nx, ny) = listOf(x + dx, y + dy)
    if (!(nx in 0 until c && ny in 0 until r)) return
    if (direction.contains(graph[ny][nx])) pipeCandidate.addAll(when(direction) {
        top -> bottom
        bottom -> top
        left -> right
        else -> left
    })
}