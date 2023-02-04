import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess


private val br = BufferedReader(InputStreamReader(System.`in`))
var count = 0
var n = 0
val numbers = mutableListOf<Long>()

fun main(args: Array<String>) {
    n = br.readLine().toInt()


    for(digit in 2 .. 10) {
        dfs(10, digit, mutableListOf())
    }

    if(n >= numbers.size + 10)
        println(-1)
    else if(n < 10)
        println(n)
    else
        println(numbers[n - 10])
}

// 맨 앞자리 숫자가 start로 시작하는 digit 자리수 만큼의 감소하는 수를 모두 구한다.
fun dfs(start: Int, digit: Int, stk: MutableList<Int>) {

    if(digit == 0) {
        numbers.add(stk.joinToString("").toLong())
    }

    for(next in 0 until start) {
        stk.add(next)
        dfs(next, digit - 1, stk)
        stk.removeLast()
    }
}