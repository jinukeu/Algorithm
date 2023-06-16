
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.*
import java.util.Collections

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
val nums = mutableListOf<Int>()

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }


    repeat(n) {
        nums.add(br.readLine().toInt())
    }

    var start = 1
    var end = nums.sum()
    var ans = Int.MAX_VALUE

    while (start <= end) {
        val mid = (start + end) / 2
        val tempM = getM(mid)

        if(tempM > m) start = mid + 1
        else {
            ans = min(ans, mid)
            end = mid - 1
        }
    }

    println(ans)
}

fun getM(k: Int): Int {
    var money = 0
    var count = 0

    for(num in nums) {
        if(num > k) return Int.MAX_VALUE

        if(num > money) {
            money = k // 남은 돈 통장에 집어넣고 다시 k원 인출
            count += 1
        }

        money -= num
    }

    return count
}