import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.min

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

val dp = Array(61) {Array(61) {Array(61) {0} }}

fun main() {
    val n = br.readLine().toInt()
    val scvs = br.readLine().split(" ").map { it.toInt() }.toMutableList()

    repeat(3 - n) {
        scvs.add(0)
    }

    println(attack(scvs[0], scvs[1], scvs[2]))
}

fun attack(h1:Int, h2:Int, h3:Int) : Int{

    // 0보다 작은 값이라면 0으로 만들어줌
    if(h1<0) return attack(0,h2,h3)
    if(h2<0) return attack(h1,0,h3)
    if(h3<0) return attack(h1,h2,0)

    // SCV 가 전부 죽었다면 리턴
    if(h1 == 0 && h2 ==0 && h3 == 0){
        return 0
    }

    // 동일한 연산을 했을 경우 리턴
    if(dp[h1][h2][h3] != 0) return dp[h1][h2][h3]

    // 모든 경우의 수 탐색
    dp[h1][h2][h3] = 100000
    dp[h1][h2][h3] = min(dp[h1][h2][h3],attack(h1-9,h2-3,h3-1)+1)
    dp[h1][h2][h3] = min(dp[h1][h2][h3],attack(h1-9,h2-1,h3-3)+1)
    dp[h1][h2][h3] = min(dp[h1][h2][h3],attack(h1-3,h2-9,h3-1)+1)
    dp[h1][h2][h3] = min(dp[h1][h2][h3],attack(h1-1,h2-9,h3-3)+1)
    dp[h1][h2][h3] = min(dp[h1][h2][h3],attack(h1-3,h2-1,h3-9)+1)
    dp[h1][h2][h3] = min(dp[h1][h2][h3],attack(h1-1,h2-3,h3-9)+1)

    return dp[h1][h2][h3]
}