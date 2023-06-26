
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val n = br.readLine().toInt()
    val musics = listOf(0) + br.readLine().split(" ").map { it.toInt() } + listOf(Int.MAX_VALUE)

    val mistakes = Array(n + 2) { 0 }
    val mistakesFlag = Array(n + 2) { false }
    repeat(n + 1) { idx ->
        mistakes[idx + 1] = mistakes[idx]
        if(musics[idx] > musics[idx + 1]) {
            mistakesFlag[idx] = true
            mistakes[idx + 1] = mistakes[idx] + 1
        }
    }

    repeat(br.readLine().toInt()) {
        val (x, y) = br.readLine().split(" ").map { it.toInt() }
        bw.write("${mistakes[y + 1] - mistakes[x] - if(mistakesFlag[y]) 1 else 0}\n")
    }
    
    bw.flush()

}