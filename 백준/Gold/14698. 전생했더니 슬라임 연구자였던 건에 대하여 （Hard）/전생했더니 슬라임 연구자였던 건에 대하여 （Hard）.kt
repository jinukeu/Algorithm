import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.contracts.contract

const val MOD = 1_000_000_007
private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main(args: Array<String>) {
    val t = br.readLine().toInt()
    repeat(t) {
        getTotalCost()
    }

    bw.flush()
    bw.close()
}

fun getTotalCost() {
    val n = br.readLine().toInt()
    val slimes = br.readLine().split(" ").map { it.toLong() }
    val pq = PriorityQueue<Long>()
    pq.addAll(slimes)

    var totalCost = 1L

    while (pq.size > 1) {
        val a = pq.poll()
        val b = pq.poll()

        val newSlime = (a * b)

        totalCost *= newSlime % MOD
        totalCost %= MOD

        pq.offer(newSlime)
    }

    bw.write("${totalCost % MOD}\n")
}