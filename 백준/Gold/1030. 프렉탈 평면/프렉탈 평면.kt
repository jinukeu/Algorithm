
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val list = br.readLine().split(" ").map { it.toLong() }
    val s = list[0]
    val n = list[1]
    val k = list[2]
    val r1 = list[3]
    val r2= list[4]
    val c1 = list[5]
    val c2 = list[6]

    val size = n.toDouble().pow(s.toInt()).toLong()

    for(r in r1 .. r2) {
        for(c in c1 .. c2) {
            print(divideAndConquer(size, r, c, n, k))
        }
        println()
    }
}

fun divideAndConquer(range: Long, r: Long, c: Long, n: Long, k: Long): Int {
    val center = range / n
    if(range == 1L)
        return 0
    if(c in center * (n - k) / 2 until center * (n + k) / 2 && r in center * (n - k) / 2 until center * (n + k) / 2)
        return 1

    return divideAndConquer(range / n, r % center, c % center, n, k)
}

