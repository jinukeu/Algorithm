
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min
import kotlin.math.pow

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

val MAX_VALUE = 100_000_000

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { Array(n + 1) { MAX_VALUE } }

    for(a in 1 .. n)
        for(b in 1 .. n) {
            if(a == b) graph[a][b] = 0
        }

    repeat(m) {
        val (u, v, b) = br.readLine().split(" ").map { it.toInt() }
        graph[u][v] = 0
        if(b == 1) graph[v][u] = 0
    }


    for(k in 1 .. n) {
        for(a in 1 .. n) {
            for(b in 1 .. n) {
                graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])
                /**
                 * a -> k 양항뱡으로 바꿈, k -> b 양방향으로 안바꿈
                 * a -> k 안바꿈, k -> b 바꿈
                 * a -> k 바꿈, k -> b 바꿈
                 */
                if(graph[a][b] != MAX_VALUE) continue

                // a -> k로는 갈 수 없는데 k -> b로는 갈 수 있는 경우, 그리고 a -> k를 양방향으로 만들 수 있는 경우
                if(graph[a][k] == MAX_VALUE && graph[k][b] != MAX_VALUE && graph[k][a] != MAX_VALUE) {
                    graph[a][b] = 1 + graph[k][b]
                }
                // k -> b로는 갈 수 없는데 a -> k로는 갈 수 있는 경우, 그리고 k -> b를 양방향으로 만들 수 있는 경우
                else if(graph[k][b] == MAX_VALUE && graph[a][k] != MAX_VALUE && graph[b][k] != MAX_VALUE) {
                    graph[a][b] = 1 + graph[a][k]
                }
                else if(graph[k][b] == MAX_VALUE && graph[b][k] != MAX_VALUE && graph[a][k] == MAX_VALUE && graph[k][a] != MAX_VALUE) {
                    graph[a][b] = 2
                }
            }
        }
    }




    repeat(br.readLine().toInt()) {
        val (s, e) = br.readLine().split(" ").map { it.toInt() }
        bw.write("${graph[s][e]}\n")
    }
    bw.flush()
}

