import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val graph = mutableListOf<List<GraphInfo>>()

    repeat(n) {
        graph.add(br.readLine().split(" ").map { color -> GraphInfo(color.toInt()) })
    }

    val chessList = mutableListOf<Chess>()

    repeat(k) { id ->
        val (r, c, d) = br.readLine().split(" ").map { it.toInt() }
        chessList.add(Chess(id, r - 1, c - 1, d))
        graph[r - 1][c - 1].chessIds.add(id)
    }

    var turn = 1

    while (turn <= 1000) {
        for (chess in chessList) {
            val (r, c) = chess.getMoveTarget()
            val (pr, pc) = (chess.row to chess.col)

            val idx = graph[pr][pc].chessIds.indexOfFirst { id -> id == chess.id }
            val toMove = mutableListOf<Int>()
            val remain = mutableListOf<Int>()

            graph[pr][pc].chessIds.forEachIndexed { index, it ->
                if(index < idx) remain.add(it)
                else toMove.add(it)
            }

//            println("움직일 체스 id = ${chess.id + 1}")
//            println("현재 체스 판의 말 쌓인 순서 : ${graph[pr][pc].chessIds.map { it + 1 }}")
//            println("toMove = ${toMove.map { it + 1 }}")
//            println("remain = ${remain.map { it + 1 }}")


            if(r !in 0 until  n || c !in 0 until n || graph[r][c].color == 2) {
                // 파란색 동작 수행
                chess.reverse()
                val (nr, nc) = chess.getMoveTarget()
                if(nr !in 0 until  n || nc !in 0 until n || graph[nr][nc].color == 2) {
                    continue
                }
                when(graph[nr][nc].color) {
                    0 -> {
                        chess.move()

                        graph[pr][pc].chessIds.forEachIndexed { index, id ->
                            if(index > idx) {
                                val newC = chessList.find { ch -> ch.id == id }!!
                                newC.row = chess.row
                                newC.col = chess.col
                            }
                        }

                        graph[nr][nc].chessIds.addAll(toMove)
                        if(graph[nr][nc].chessIds.size >= 4) {
                            println(turn)
                            return
                        }
                        graph[pr][pc].chessIds = remain
                    }
                    1 -> {
                        chess.move()

                        graph[pr][pc].chessIds.forEachIndexed { index, id ->
                            if(index > idx) {
                                val newC = chessList.find { ch -> ch.id == id }!!
                                newC.row = chess.row
                                newC.col = chess.col
                            }
                        }

                        graph[nr][nc].chessIds.addAll(toMove.reversed())
                        if(graph[nr][nc].chessIds.size >= 4) {
                            println(turn)
                            return
                        }
                        graph[pr][pc].chessIds = remain
                    }
                    else -> {}
                }
            } else if(graph[r][c].color == 0) {
                chess.move()


                graph[pr][pc].chessIds.forEachIndexed { index, id ->
                    if(index > idx) {
                        val newC = chessList.find { ch -> ch.id == id }!!
                        newC.row = chess.row
                        newC.col = chess.col
                    }
                }

                graph[r][c].chessIds.addAll(toMove)
                if(graph[r][c].chessIds.size >= 4) {
                    println(turn)
                    return
                }
                graph[pr][pc].chessIds = remain
            } else if(graph[r][c].color == 1) {
                chess.move()

                graph[pr][pc].chessIds.forEachIndexed { index, id ->
                    if(index > idx) {
                        val newC = chessList.find { ch -> ch.id == id }!!
                        newC.row = chess.row
                        newC.col = chess.col
                    }
                }

                graph[r][c].chessIds.addAll(toMove.reversed())
                if(graph[r][c].chessIds.size >= 4) {
                    println(turn)
                    return
                }
                graph[pr][pc].chessIds = remain
            }

//            chessList.forEach {
//                println(it.copy(row = it.row + 1, col = it.col + 1))
//            }
//
//            graph.forEach { g ->
//                g.forEach {
//                    if(it.chessIds.isNotEmpty()) println(it.chessIds.map { ch -> ch + 1 })
//                }
//            }
//            println("---")
        }
        turn += 1
    }

    println(-1)

}

data class GraphInfo(
    val color: Int,
    var chessIds: MutableList<Int> = mutableListOf(),
)

data class Chess(
    val id: Int,
    var row: Int,
    var col: Int,
    var dir: Int
) {
    /**
     * row, col
     */
    fun getMoveTarget(): Pair<Int, Int> {
        return when(dir) {
            1 -> Pair(row, col + 1)
            2 -> Pair(row, col - 1)
            3 -> Pair(row - 1, col)
            else -> Pair(row + 1, col)
        }
    }

    fun move() {
        when(dir) {
            1 -> col += 1
            2 -> col -= 1
            3 -> row -= 1
            else -> row += 1
        }
    }

    fun reverse() {
        dir = when(dir) {
            1 -> 2
            2 -> 1
            3 -> 4
            else -> 3
        }
    }
}