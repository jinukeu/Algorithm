import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

val commands = mutableListOf<Triple<Int, Int, Int>>()

fun main() {
    var (n, hatk) = br.readLine().split(" ").map { it.toLong() }

    var maxHp = Long.MAX_VALUE

    repeat(n.toInt()) {
        val (t, a, h) = br.readLine().split(" ").map { it.toInt() }
        commands.add(Triple(t, a, h))
    }

    var start = 0L
    var end = Long.MAX_VALUE

    while (start <= end) {
        val mid = (start + end) / 2

        if(enter(mid, hatk)) {
            maxHp = min(maxHp, mid)
            end = mid - 1
        } else {
            start = mid + 1
        }
    }

    println(maxHp)
}

fun enter(hmax: Long, hat: Long): Boolean {
    var hatk = hat

    var curHp = hmax

    for((t, a, h) in commands) {
        var reducedHp = 0L
        if(t == 1) {
            reducedHp += a * (h / hatk)
            if(h % hatk == 0L) reducedHp -= a
        }
        else {
            hatk += a
            curHp += h

            if(curHp > hmax) curHp = hmax
        }

        curHp -= reducedHp

        if(curHp <= 0) return false
    }

    return true
}