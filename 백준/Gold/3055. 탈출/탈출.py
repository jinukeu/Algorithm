from collections import deque
input = __import__('sys').stdin.readline

r, c = map(int, input().split())
graph = [list(input().strip()) for _ in range(r)]
waterVisited = [[False] * c for _ in range(r)]
dociVisited = [[False] * c for _ in range(r)]
water = deque()
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

# 초기값 설정
for i in range(r):
    for j in range(c):
        if graph[i][j] == 'D':
            cave = (i, j)
        if graph[i][j] == 'S':
            doci = (i, j, 0)
            dociVisited[i][j] = True
        if graph[i][j] == '*':
            water.append((i, j))
            waterVisited[i][j] = True
      
def waterFlow():
    initLen = len(water)
    for _ in range(initLen):
        i, j = water.popleft()
        for idx in range(4):
            ni, nj = i + dy[idx], j + dx[idx]
            if check(ni, nj, waterVisited): continue
            if graph[ni][nj] == 'D' or graph[ni][nj] == 'X':
                continue
            graph[ni][nj] = '*'
            water.append((ni, nj))
            waterVisited[ni][nj] = True
                   
def check(ni, nj, visited):
    return ni < 0 or ni >= r or nj < 0 or nj >= c or visited[ni][nj]

def moveDoci():
    q = deque()
    q.append(doci)
    cnt = 0
    prevCnt = -1
    while q:
        i, j, cnt = q.popleft()
        if prevCnt < cnt:
            prevCnt = cnt
            waterFlow()
        if ((i, j) == cave):
            print(cnt)
            return
        for idx in range(4):
            ni, nj = i + dy[idx], j + dx[idx]
            if check(ni, nj, dociVisited): continue
            if graph[ni][nj] == '*' or graph[ni][nj] == 'X':
                continue
            q.append((ni, nj, cnt + 1))
            dociVisited[ni][nj] = True
    print("KAKTUS")

moveDoci()