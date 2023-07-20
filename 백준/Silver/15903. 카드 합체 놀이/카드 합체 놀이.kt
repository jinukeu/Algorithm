import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val cards = br.readLine().split(" ").map { it.toLong() }

    val pq = PriorityQueue<Long>()
    pq.addAll(cards)

    repeat(m) {
        val new = pq.poll() + pq.poll()
        pq.add(new)
        pq.add(new)
    }

    println(pq.sum())
}