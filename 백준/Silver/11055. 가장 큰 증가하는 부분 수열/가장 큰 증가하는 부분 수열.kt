import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections


fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val nums = listOf(0) + br.readLine().split(" ").map { it.toInt() }
    val dp = IntArray(n + 1)

    for(index in 1 .. n) {
        var prevMax = 0

        for(j in index - 1 downTo 0) {
            if(nums[j] < nums[index] && prevMax < dp[j]) {
                prevMax = dp[j]
            }
        }

        dp[index] = prevMax + nums[index]
    }

    //println(dp.contentToString())
    println(dp.maxOrNull())

}