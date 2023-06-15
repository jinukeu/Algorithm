import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.system.exitProcess

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val nums = br.readLine().split(" ").map { it.toInt() }.sorted()

    repeat(m) {
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        bw.write("${nums.bSearchRight(m) - nums.bSearchLeft(n)}\n")
    }

    bw.flush()
}

fun List<Int>.bSearchLeft(value: Int): Int {
    var low = 0
    var high = this.lastIndex
    var mid = 0

    while (low <= high) {
        mid = (low + high) / 2

        when {
            this[mid] < value   -> low = mid + 1
            this[mid] > value   -> high = mid - 1
            this[mid] == value  -> return mid
        }
    }

    return low
}

fun List<Int>.bSearchRight(value: Int): Int {
    var low = 0
    var high = this.lastIndex
    var mid = 0

    while (low <= high) {
        mid = (low + high) / 2

        when {
            this[mid] < value   -> low = mid + 1
            this[mid] > value   -> high = mid - 1
            this[mid] == value  -> return mid + 1
        }
    }

    return high + 1
}