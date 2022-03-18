import sys, heapq
input = sys.stdin.readline

n, m = map(int, input().split())
graph = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

def dijk(start, graph):
    # 비용, 지점
    que = [(0, start)]
    dist = [1e9] * (n + 1)
    dist[start] = 0
    ans = [start for _ in range(n + 1)]
    while que:
        cost, now = heapq.heappop(que)  
        if cost > dist[now]:
            continue
        
        #now -> node
        for node, throughNode in graph[now]:
            if throughNode + cost < dist[node]:
                dist[node] = throughNode + cost
                ans[node] = now
                heapq.heappush(que, (throughNode + cost, node))
    
    for i in range(1, n + 1):
        if i == start:
            print('-', end = " ")
            continue
        j = i
        while True:
            if ans[j] == start:
                print(j, end=" ")
                break
            else:
                j = ans[j]
    print()
            

for i in range(1, n + 1):
    dijk(i, graph)