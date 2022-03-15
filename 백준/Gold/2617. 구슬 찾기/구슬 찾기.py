n, m = map(int, input().split())
heavyGraph = [[] for _ in range(n + 1)]
lightGraph = [[] for _ in range(n + 1)]
cnt = 0

for _ in range(m):
    a, b = map(int, input().split())
    heavyGraph[b].append(a)
    lightGraph[a].append(b)

def findCnt(findIdx, graph, visited):
    visited[findIdx] = True
    tmp = 0
    for g in graph[findIdx]:
        if visited[g]:    continue
        tmp += findCnt(g, graph, visited) + 1
    return tmp


for i in range(1, n + 1):
    visited = [False] * (n + 1)
    if findCnt(i, heavyGraph, visited) > (n//2):
        cnt += 1

for i in range(1, n + 1):
    visited = [False] * (n + 1)
    if findCnt(i, lightGraph, visited) > (n//2):
        cnt += 1

print(cnt)