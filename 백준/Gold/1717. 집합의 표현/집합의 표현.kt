import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val parent = Array(n + 1) { it }

    repeat(m) {
        val (a, x, y) = br.readLine().split(" ").map { it.toInt() }
        if(a == 0) unionParent(parent, x, y)
        else {
            println(if(findParent(parent, x) == findParent(parent, y)) "YES" else "NO")
        }
    }
}

fun findParent(parent: Array<Int>, x: Int): Int {
    if(parent[x] != x) parent[x] = findParent(parent, parent[x])
    return parent[x]
}

fun unionParent(parent: Array<Int>, x: Int, y: Int) {
    val pa = findParent(parent, x)
    val pb = findParent(parent, y)

    if(pa < pb)
        parent[pb] = pa
    else
        parent[pa] = pb
}