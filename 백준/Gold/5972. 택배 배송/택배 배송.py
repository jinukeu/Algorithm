import heapq, sys
input = sys.stdin.readline

n, m = map(int, input().split())
graph = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

def dijk(start, graph):
    heap = [(0, start)]
    distance = [1e9] * (n + 1)
    distance[start] = 0
    
    while heap:
        nowCost, nowNode = heapq.heappop(heap)
        if nowCost > distance[nowNode]:
            continue
        for nextNode, nextCost in graph[nowNode]:
            if nextCost + nowCost < distance[nextNode]:
                distance[nextNode] = nextCost + nowCost
                heapq.heappush(heap, (nextCost + nowCost, nextNode))
    
    print(distance[n])

dijk(1, graph)