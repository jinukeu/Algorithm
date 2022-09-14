import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = mutableListOf<List<Int>>()
    repeat(n) {
        graph.add(br.readLine().split(" ").map { it.toInt() })
    }

    // y, x
    val left = listOf(1, -1)
    val middle = listOf(1, 0)
    val right = listOf(1, 1)

    //first : 현재 좌표, second : left, middle, right, 비용 : cost
    var que : Queue<Triple<List<Int>, List<Int>, Int>> = LinkedList()
    repeat(m) {
        for(dir in listOf(left, middle, right))
        que.add(Triple(listOf(0, it), dir, graph[0][it]))
    }

    var ans = Int.MAX_VALUE

    while (que.isNotEmpty()) {
        var (now, prevDir, cost) = que.poll()
        if(now[0] + 1 == n) {
            if(ans > cost) ans = cost
            continue
        }

        for(nextDir in listOf(left, middle, right)) {
            if(nextDir == prevDir) continue
            if(now[1] + nextDir[1] !in 0 until m) continue
            val nextCost = cost + graph[now[0] + nextDir[0]][now[1] + nextDir[1]]
            val nextNow = listOf(now[0] + nextDir[0], now[1] + nextDir[1])
            que.add(Triple(nextNow, nextDir, nextCost))
        }
    }

    println(ans)
}