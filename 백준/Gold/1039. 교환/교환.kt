import java.util.*
import kotlin.math.max

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val strN = n.toString().toCharArray()
    val queue: Queue<CharArray> = LinkedList()
    val visited = Array(k + 1) { BooleanArray(1000001) { false } }
    queue.add(strN)
    visited[0][strN.joinToString("").toInt()] = true
    repeat(k) {
        for (r in 0 until queue.size) {
            val now = queue.poll()
            for (i in strN.indices)
                for (j in i + 1 until strN.size) {
                    val tmp = now.copyOf()
                    val t = tmp[i]
                    tmp[i] = tmp[j]
                    tmp[j] = t
                    if (tmp[0] != '0' && !visited[it + 1][tmp.joinToString("").toInt()]) {
                        visited[it + 1][tmp.joinToString("").toInt()] = true
                        queue.add(tmp)
                    }
                }
        }
    }
    var ans = -1
    while (queue.isNotEmpty()) {
        val q = queue.poll()
        ans = max(ans, q.joinToString("").toInt())
    }
    print(ans)
}