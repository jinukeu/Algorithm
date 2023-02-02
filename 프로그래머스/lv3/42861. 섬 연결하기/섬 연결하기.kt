class Solution {
    fun solution(n: Int, costs: Array<IntArray>): Int {
    var answer = 0
    costs.sortBy { it[2] }
    val parent = Array(n + 1) { it }

    for((a, b, cost) in costs) {
        if(findParent(parent, a) != findParent(parent, b)) {
            unionParent(parent, a, b)
            answer += cost
        }
    }

    return answer
}

fun unionParent(parent: Array<Int>, a: Int, b: Int) {
    val parentA = findParent(parent, a)
    val parentB = findParent(parent, b)

    if(parentA < parentB)
        parent[parentB] = parentA
    else
        parent[parentA] = parentB
}

fun findParent(parent: Array<Int>, x: Int): Int {
    if(parent[x] != x)
        parent[x] = findParent(parent, parent[x])
    return parent[x]
}
}