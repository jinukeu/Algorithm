import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.abs
import java.lang.Math.min
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Collections
import kotlin.system.exitProcess

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val n = br.readLine().toInt()
    val snows = br.readLine().split(" ").map { it.toInt() }.sorted()


    var ans = Int.MAX_VALUE


    for(bs in 0 until n) {
        for(be in bs + 1 until n) {
            var ss = bs + 1
            var se = be - 1

            val anna = snows[bs] + snows[be]

            while (ss < se) {
                val elsa = snows[ss] + snows[se]
                if(ss != se) ans = min(ans, abs(anna - elsa))
                if(anna > elsa) {
                    if(ss < n - 1) ss += 1
                } else {
                    if(se > 0) se -= 1
                }
            }
        }
    }


    println(ans)
}
