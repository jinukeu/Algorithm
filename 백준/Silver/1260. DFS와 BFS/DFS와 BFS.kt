import java.io.BufferedReader
import java.io.InputStreamReader

private val br = BufferedReader(InputStreamReader(System.`in`))
private lateinit var graph: Array<MutableList<Int>>
private lateinit var visited: Array<Boolean>
private var answerDfs = ""
private var answerBfs = ""

fun main(args: Array<String>) {
    val (n, m, v) = br.readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { mutableListOf() }
    repeat(m) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    repeat(n + 1) { idx ->
        graph[idx].sort()
    }

    visited = Array(n + 1) { false }
    dfs(v)
    println(answerDfs)

    visited = Array(n + 1) { false }
    bfs(v)
    println(answerBfs)
}

fun dfs(start: Int) {
    visited[start] = true // 2-1. (c) 스택의 최상단 노드 방문 처리
    answerDfs += "$start "

    // 2. 스택의 최상단 노드와 인접한 노드 탐색!
    for(next in graph[start]) {
        if(!visited[next])// 2-1. (a) 방문하지 않은 노드가 있다면?
            dfs(next) // 2-1. (b) 해당 노드를 스택에 넣고
    }
}

fun bfs(start: Int) {
    val queue = ArrayDeque<Int>()
    queue.add(start) // 1. 탐색 시작 노드를 큐에 삽입하고
    visited[start] = true // 1. 방문 처리

    while (queue.isNotEmpty()) {
        val next = queue.removeFirst() // 2. (a) 큐에서 해당 노드를 꺼낸 뒤에

        answerBfs += "$next "

        // 2. (b) 해당 노드의 인접 노드 중에서
        for(node in graph[next]) {
            // 2. (c) 방문하지 않은 노드를 모두 큐에 삽입하고 방문 처리
            if(!visited[node]) {
                queue.add(node)
                visited[node] = true
            }
        }
    }
}