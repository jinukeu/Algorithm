import kotlin.math.*

class Solution {
    var answer = 1000

fun solution(n: Int, weak: IntArray, dist: IntArray): Int {


    val weakCnt = weak.size
    val distCnt = dist.size

    val adjNode = mutableListOf<MutableList<NodeDist>>()

    for (idx in weak.indices) {
        val tempList = mutableListOf<NodeDist>()

        val prevNodeIdx = if (idx == 0) weak.size - 1 else idx - 1
        val nextNodeIdx = if (idx + 1 == weak.size) 0 else idx + 1

        val prevNodeDistClockWise = abs(weak[idx] - weak[prevNodeIdx])
        val prevNodeDist = min(prevNodeDistClockWise, n - prevNodeDistClockWise)

        val nextNodeDistClockWise = abs(weak[idx] - weak[nextNodeIdx])
        val nextNodeDist = min(nextNodeDistClockWise, n - nextNodeDistClockWise)

        tempList.add(NodeDist(prevNodeIdx, prevNodeDist))
        tempList.add(NodeDist(nextNodeIdx, nextNodeDist))

        adjNode.add(tempList)
    }

    /**
     * 1. 방문하지 않은 노드를 재귀에 넣는다., 함수에 visited도 넣어줘야할 듯 or 백트래킹
     * 2. 인접한 노드 방문, dist는 가장 큰 값
     * 2-1. 더 갈 수 있다면 더 감, dist 값 빼주기
     * 2-2. 더 못가면 stop, dist 다음 배열로 이동
     *
     */
    val visited = Array(adjNode.size) {false}
    for (idx in adjNode.indices) {
        visited[idx] = true
        dfs(adjNode, visited, dist.last(), idx, dist.size - 1, dist, 0)
        visited[idx] = false
    }

    if(answer == 1000) answer = -1
    return answer
}

fun dfs(
    adjNodes: MutableList<MutableList<NodeDist>>,
    visited: Array<Boolean>,
    travelableDistance: Int,
    currentNodeIdx: Int,
    currentDistIdx: Int,
    dist: IntArray,
    count: Int
)
{
    if(visited.find { it == false } == null) {
        answer = min(answer, dist.size - currentDistIdx)
    }
    // 인접한 노드 방문
    var isVisitedAdj = false
    for (adjNode in adjNodes[currentNodeIdx]) {
        if(visited[adjNode.node] || travelableDistance < adjNode.dist) continue
        visited[adjNode.node] = true
        isVisitedAdj = true
        dfs(adjNodes, visited, travelableDistance - adjNode.dist , adjNode.node, currentDistIdx, dist, count + 1)
        visited[adjNode.node] = false
    }

    if(isVisitedAdj.not()) {
        for (idx in adjNodes.indices) {
            if (visited[idx] || currentDistIdx == 0) continue
            visited[idx] = true
            dfs(adjNodes, visited, dist[currentDistIdx - 1], idx, currentDistIdx - 1, dist, count + 1)
            visited[idx] = false
        }
    }
}

data class NodeDist(
    val node: Int,
    val dist: Int
)
}