import java.io.BufferedReader
import java.io.InputStreamReader


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val (n, w) = br.readLine().split(" ").map { it.toInt() }
    val tempGraph = List<MutableList<Int>>(n + 1) { mutableListOf() }
    val parent = MutableList(n + 1) { it }
    repeat(n - 1) {
        val (u, v) = br.readLine().split(" ").map { it.toInt() }
        tempGraph[u].add(v)
        tempGraph[v].add(u)
    }

    val graph = List<MutableList<Int>>(n + 1) { mutableListOf() }

    for(idx in 1 until tempGraph.size) {
        tempGraph[idx].forEach { child ->
            if(child == parent[child] && child != 1 && parent[child] != 1) {
                parent[child] = idx
                graph[idx].add(child)
            }
        }
    }


    var leafN = 0
    for(i in 2 .. n) {
        if(tempGraph[i].size == 1) leafN += 1
    }
    val answer = w.toDouble() / leafN
//    println((graph.count { it.size == 0 } - 1))
//    println(answer)
    println(String.format("%.10f", answer))
}