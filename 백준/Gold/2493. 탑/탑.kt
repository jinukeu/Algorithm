import java.io.BufferedReader
import java.io.InputStreamReader


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    br.readLine()
    val topHeights = br.readLine().split(" ").map { it.toInt() }
    val stack = mutableListOf<Top>()
    val answer = mutableListOf<Int>()

    topHeights.forEachIndexed { index, topHeight ->
        if(stack.isEmpty()) {
            stack.add(Top(index + 1, topHeight))
            answer.add(index)
        }
        else {
            while (stack.isNotEmpty() && stack.last().height < topHeight) {
                stack.removeLast()
            }
            if(stack.isEmpty()) {
                answer.add(0)
                stack.add(Top(index + 1, topHeight))
            }
            else {
                answer.add(stack.last().index)
                stack.add(Top(index + 1, topHeight))
            }
        }
    }

    print(answer.joinToString(" "))
    //만약 더 큰거 발견하면 그거 그냥 pop 해버리기
}

data class Top(
    val index: Int,
    val height: Int
)
