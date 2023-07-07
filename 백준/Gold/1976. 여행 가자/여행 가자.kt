import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val graph = mutableListOf<List<Int>>(emptyList())

    repeat(n) {
        graph.add(listOf(0) + br.readLine().split(" ").map { it.toInt() })
    }

    val parent = Array(n + 1) { it }

    for(i in 1 .. n)
        for(j in 1 .. n) {
            if(graph[i][j] == 1) unionParent(parent, i, j)
        }

    val jo = br.readLine().split(" ").map { it.toInt() }

    var answer = "YES"
    for(i in 0 until jo.size - 1) {
        if(parent[jo[i]] != parent[jo[i + 1]]) {
            answer = "NO"
            break
        }
    }

    println(answer)
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