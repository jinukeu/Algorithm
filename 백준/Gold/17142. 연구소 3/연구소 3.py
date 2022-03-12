from collections import deque
from itertools import combinations

def bfs(que, lab, blankCnt, visited):
    global n
    dir = ((0, 1), (1, 0), (0, -1), (-1, 0))
    time = 0
    while que:
        if not blankCnt:
            return time, blankCnt
        for _ in range(len(que)):
            i, j = que.popleft()
            for di, dj in dir:
                ni, nj = i + di, j + dj
                if ni < 0 or ni >= n or nj < 0 or nj >= n or visited[ni][nj] or lab[ni][nj] == 1:
                    continue
                if not lab[ni][nj]:
                    blankCnt -= 1
                visited[ni][nj] = True
                que.append((ni, nj))
        time += 1
    return time, blankCnt
            

n, m = map(int, input().split())
lab = [list(map(int, input().split())) for _ in range(n)]
blankCnt = 0
virusLocation = []

for i in range(n):
    for j in range(n):
        if lab[i][j] == 2:
            virusLocation.append((i, j))
        elif lab[i][j] == 0:
            blankCnt += 1

ans = 2500
for combi in list(combinations(virusLocation, m)):
    que = deque()
    visited = [[False] * n for _ in range(n)]
    for c in combi:
        que.append(c)
        visited[c[0]][c[1]] = True
    time, b = bfs(que, lab, blankCnt, visited)
    if not b:
        ans = min(ans, time)

if ans == 2500:
    print(-1)
else:
    print(ans)