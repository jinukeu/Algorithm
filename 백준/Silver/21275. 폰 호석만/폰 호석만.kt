import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Long.parseLong


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val (xa, xb) = br.readLine().split(" ")
    val answers = mutableListOf<Answer>()

    for(a in 2 .. 36) {
        for(b in 2 .. 36) {
            if(a == b) continue

            try {
                val ax = parseLong(xa,a)
                val bx = parseLong(xb,b)
                if(ax == bx) {
                    answers.add(Answer(ax, a, b))
                }
            } catch (e: Exception) { }
        }
    }

    if(answers.isEmpty()) println("Impossible")
    else if(answers.size == 1) println("${answers[0].x} ${answers[0].a} ${answers[0].b}")
    else println("Multiple")
}

data class Answer(
    val x: Long,
    val a: Int,
    val b: Int
)