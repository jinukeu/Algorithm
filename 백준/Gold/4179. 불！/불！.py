from collections import deque
import sys
input = sys.stdin.readline
def findJihon(maze, que, fque):
    for i in range(n):
        for j in range(m):
            if maze[i][j] == 'J':
                maze[i][j] = '.'
                que.append((i, j, 0))
            if maze[i][j] == 'F':
                fque.append((i, j))

n, m = map(int, input().split())
maze = [list(input().rstrip()) for _ in range(n)]
visited = [[False] * m for _ in range(n)]
fireVisited = [[False] * m for _ in range(n)]
dir = ((0, 1), (1,0), (-1,0),(0, -1))
que = deque()
fque = deque()
findJihon(maze, que, fque)

while que:
    for _ in range(len(que)):
        i, j, time = que.popleft()
        if maze[i][j] == 'F':
            continue
        if i == 0 or i == (n - 1) or j == 0 or j == (m - 1):
            print(time + 1)
            exit()
        for di, dj in dir:
            ni, nj = i + di, j + dj
            if ni < 0 or ni >= n or nj < 0 or nj >= m or visited[ni][nj] or maze[ni][nj] != '.':  continue
            visited[ni][nj] = True
            que.append((ni, nj, time + 1))
    
    for _ in range(len(fque)):
        i, j = fque.popleft()
        for di, dj in dir:
            ni, nj = i + di, j + dj
            if ni < 0 or ni >= n or nj < 0 or nj >= m or maze[ni][nj] != '.':  continue
            maze[ni][nj] = 'F'
            fque.append((ni, nj))

print("IMPOSSIBLE")