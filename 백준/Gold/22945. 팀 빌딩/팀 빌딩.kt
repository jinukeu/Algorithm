import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Collections
import kotlin.system.exitProcess

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val n = br.readLine().toInt()
    val levels = br.readLine().split(" ").map { it.toInt() }

    var ans = 0

    var (startIdx, endIdx) = listOf(0, levels.size - 1)

    while (startIdx != endIdx) {
        val temp = min(levels[startIdx], levels[endIdx]) * (endIdx - startIdx - 1)
        ans = max(ans, temp)

        if(levels[startIdx] < levels[endIdx]) {
            startIdx += 1
        } else {
            endIdx -= 1
        }
    }

    println(ans)
}