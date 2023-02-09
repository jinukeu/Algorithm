import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.system.exitProcess


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val pq = PriorityQueue<Int>(Collections.reverseOrder())
    repeat(br.readLine().toInt()) {
        val a = br.readLine()
        if(a == "0") {
            if(pq.isEmpty()) println(-1)
            else println(pq.poll())
        } else {
            val gifts = a.split(" ").reversed().dropLast(1)
            for(gift in gifts)
                gift.toIntOrNull()?.let { pq.add(it) }
        }
    }
}