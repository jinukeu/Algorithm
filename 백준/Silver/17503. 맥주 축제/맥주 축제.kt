import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
    val beerList = mutableListOf<Beer>()
    repeat(k) {
        // v: 맥주 선호도 c: 도수 레벨
        val (v, c) = br.readLine().split(" ").map { it.toInt() }
        beerList.add(Beer(v, c))
    }

    beerList.sortWith(compareBy<Beer> { -it.v }.thenBy { -it.c })

    var check = 0

    repeat(n) {
        check += beerList[it].v
    }

    if(check < m) {
        println(-1)
        return
    }

    var start = 0L
    var end = Int.MAX_VALUE.toLong()

    var ans = Int.MAX_VALUE.toLong()

    while (start <= end) {
        var mid = (start + end) / 2

        var tempM = mutableListOf<Int>()
        
        
        for(beer in beerList) {
            if(beer.c <= mid) {
                tempM += beer.v
            }

            if(tempM.size == n) break
        }
        

        if(tempM.size < n) {
            start = mid + 1
            continue
        }

        if(tempM.sum() >= m) {
            end = mid - 1
            ans = min(ans, mid)
        } else {
            start = (mid + 1)
        }
    }

    println(ans)
}

/**
 * v : 맥주 선호도
 * c : 도수 레벨
 */
data class Beer(
    val v: Int,
    val c: Int
)