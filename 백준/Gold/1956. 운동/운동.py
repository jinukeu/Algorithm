v, e = map(int,input().split())
graph = [[1e9] * (v + 1) for _ in range(v + 1)]

for _ in range(e):
    a, b, c = map(int, input().split())
    graph[a][b] = c

for i in range(1, v + 1):
    for j in range(1, v + 1):
        if i == j:
            graph[i][j] = 0

for k in range(1, v + 1):
    for i in range(1, v + 1):
        for j in range(1, v + 1):
            graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

ans = 1e9
for i in range(1, v + 1):
    for j in range(1, v + 1):
        if graph[i][j] != 0 and graph[i][j] != 1e9 and graph[j][i] != 0 and graph[j][i] != 1e9:
            ans = min(graph[i][j] + graph[j][i], ans)

if ans == 1e9:
    print(-1)
else:
    print(ans)