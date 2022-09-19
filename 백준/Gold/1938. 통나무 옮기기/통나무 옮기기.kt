import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import kotlin.system.exitProcess

private val br = BufferedReader(InputStreamReader(System.`in`))
private lateinit var verticalVisited: Array<Array<Boolean>>
private lateinit var horizontalVisited: Array<Array<Boolean>>
private lateinit var start: Log
private lateinit var end: Log
private lateinit var queue: Queue<LogCnt>
private var n = 0
val graph = mutableListOf<List<String>>()

fun main(args: Array<String>) {
    n = br.readLine().toInt()
    verticalVisited = Array(n) { Array(n) { false } }
    horizontalVisited = Array(n) { Array(n) { false } }
    queue = LinkedList()

    repeat(n) {
        graph.add(br.readLine().split("").subList(1, n + 1))
    }

    var bCnt = 0
    var eCnt = 0
    var firstBI = 0
    var firstEI = 0
    for(i in 0 until n)
        for(j in 0 until n) {
            if(graph[i][j] == "B") {
                bCnt++
                if(bCnt == 1) firstBI = i
                if(bCnt == 2) {
                    start = Log(j, i, i != firstBI)
                    start.setVisited()
                }
            }

            if(graph[i][j] == "E") {
                eCnt++
                if(eCnt == 1) firstEI = i
                if(eCnt == 2) {
                    end = Log(j, i, i != firstEI)
                }
            }
        }

    queue.add(LogCnt(start, 0))

    while (queue.isNotEmpty()) {
        val (log, cnt) = queue.poll()
        if(log == end) {
            print(cnt)
            exitProcess(0)
        }

        val changeLogList = listOf(log.up(), log.down(), log.left(), log.right(), log.turn())
        changeLogList.forEach { log ->
            if(log?.isValid() == true) {
                log.setVisited()
                queue.add(LogCnt(log, cnt + 1))
            }
        }
    }

    println(0)
}

data class LogCnt(
    val log: Log,
    val cnt: Int
)

data class Log (
    val midX: Int,
    val midY: Int,
    val isVertical: Boolean
) {
    fun up(): Log? {
        val condition = if(isVertical) 1 else 0
        if(midY - 1 < condition) return null
        return Log(midX, midY - 1, isVertical)
    }

    fun down(): Log? {
        val condition = if(isVertical) n - 2 else n - 1
        if(midY + 1 > condition) return null
        return Log(midX, midY + 1, isVertical)
    }

    fun left(): Log? {
        val condition = if(isVertical) 0 else 1
        if(midX - 1 < condition) return null
        return Log(midX - 1, midY, isVertical)
    }

    fun right(): Log? {
        val condition = if(isVertical) n - 1 else n - 2
        if(midX + 1 > condition) return null
        return Log(midX + 1, midY, isVertical)
    }

    fun turn(): Log? {
        if(isVertical) {
            if(midX == 0 || midX == n - 1 || midY == 0 || midY == n - 1) return null
            val left = midX - 1
            val right = midX + 1
            if (graph[midY - 1][left] == "1" || graph[midY][left] == "1" || graph[midY + 1][left] == "1") return null
            if (graph[midY - 1][right] == "1" || graph[midY][right] == "1" || graph[midY + 1][right] == "1") return null
        }

        if(!isVertical) {
            if(midX == 0 || midX == n - 1 || midY == 0 || midY == n - 1) return null
            val up = midY - 1
            val down = midY + 1
            if(graph[up][midX - 1] == "1" || graph[up][midX] == "1" || graph[up][midX + 1] == "1") return null
            if(graph[down][midX - 1] == "1" || graph[down][midX] == "1" || graph[down][midX + 1] == "1") return null
        }

        return Log(midX, midY, !isVertical)
    }

    fun isValid(): Boolean {
        if (isVertical)
            if (graph[midY - 1][midX] == "1" || graph[midY][midX] == "1" || graph[midY + 1][midX] == "1") return false
        if (!isVertical)
            if (graph[midY][midX - 1] == "1" || graph[midY][midX] == "1" || graph[midY][midX + 1] == "1") return false

        return !getIsVisited()
    }

    fun getIsVisited(): Boolean {
        return if(isVertical) verticalVisited[midY][midX]
        else horizontalVisited[midY][midX]
    }

    fun setVisited() {
        if(isVertical) verticalVisited[midY][midX] = true
        else horizontalVisited[midY][midX] = true
    }
}