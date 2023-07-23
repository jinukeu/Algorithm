import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val w = br.readLine().toCharArray()
    val s = br.readLine().toCharArray()

    val answer = hashMapOf<Char, Int>()
    val sliding = hashMapOf<Char, Int>()

    for(a in 'a' .. 'z') {
        answer[a] = 0
        sliding[a] = 0
    }

    for(a in 'A' .. 'Z') {
        answer[a] = 0
        sliding[a] = 0
    }

    for(a in w) {
        answer[a] = answer[a]!! + 1
    }

    repeat(n) {
        sliding[s[it]] = sliding[s[it]]!! + 1
    }

    var start = 0
    var end = n - 1

    var same = 0

    if(answer == sliding) {
        same += 1
    }

    while (end != m - 1) {
        sliding[s[start]] = sliding[s[start]]!! - 1
        start += 1
        end += 1
        sliding[s[end]] = sliding[s[end]]!! + 1

        if(answer == sliding) {
            same += 1
        }
    }

    println(same)

}