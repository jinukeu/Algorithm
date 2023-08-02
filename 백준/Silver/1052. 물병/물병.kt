import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    var (n, k) = br.readLine().split(" ").map { it.toInt() }

    var p = 23

    while (true) {
       if (n >= 2.0.pow(p)) {
           n -= 2.0.pow(p).toInt()
           k -= 1
       }

        if(k == 0) break
        p -= 1
    }

    println(if(n == 0) 0 else 2.0.pow(p).toInt() - n)
}