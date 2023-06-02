import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.abs
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Collections
import kotlin.system.exitProcess

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val t = br.readLine().toInt()
    repeat(t) {
        getAnswer()
    }
}

fun getAnswer() {
    val n = br.readLine().toInt()
    val coins = br.readLine().split(" ").map { it.toInt() }
    val goal = br.readLine().toInt()

    val dp = IntArray(goal + 1)
    dp[0] = 1

    coins.forEach { coin ->
        for (money in 1..goal) {
            if (money - coin >= 0)
                dp[money] += dp[money - coin]
        }
    }

    println(dp[goal])
}