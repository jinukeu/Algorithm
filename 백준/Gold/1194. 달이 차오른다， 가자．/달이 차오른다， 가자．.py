import sys
from collections import deque
input = sys.stdin.readline

key = {'a':2, 'b':3, 'c':4, 'd':5, 'e':6, 'f':7}
door = {'A':2, 'B':3, 'C':4, 'D':5, 'E':6, 'F':7}

n, m = map(int, input().split())
graph = [list(input().rstrip()) for _ in range(n)]
visited = [[0] * m for _ in range(n)]

def condition(ni, nj):
    return ni < 0 or ni >= n or nj < 0 or nj >= m or graph[ni][nj] == '#'

def bfs(y, x):
    q = deque()
    q.append((y, x, 1, 0))
    visited[y][x] |= (1 << 0)
    dir = ((0, 1), (0, -1), (1, 0), (-1, 0))
    
    while q:
        i, j, haveKey, cnt = q.popleft()
        if graph[i][j] == '1':
            print(cnt)
            return
        
        for dy, dx in dir:
            ni, nj = i + dy, j + dx
            if condition(ni, nj):   continue
            if (visited[ni][nj] | haveKey) == visited[ni][nj]: continue
            if 'A' <= graph[ni][nj] <= 'F':
                if not (haveKey & (1 << door[graph[ni][nj]])): #키가 없는 경우
                    continue
            if 'a' <= graph[ni][nj] <= 'f':
                q.append((ni, nj, haveKey | (1 << key[graph[ni][nj]]), cnt + 1))
                visited[ni][nj] = (haveKey | (1 << key[graph[ni][nj]]))
                continue
            q.append((ni, nj, haveKey, cnt + 1))
            visited[ni][nj] = haveKey
    print(-1)
            
for i in range(n):
    for j in range(m):
        if graph[i][j] == '0':
            bfs(i, j)
            break