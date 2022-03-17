import heapq, sys
input = sys.stdin.readline

m, n = map(int, input().split())
maze = [list(map(int, input().strip())) for _ in range(n)]

heap = [(0, 0, 0)]
dir = [(0,1),(1,0),(-1,0),(0,-1)]
visited = [[False] * m for _ in range(n)]
visited[0][0] = True

while heap:
    wall, i, j = heapq.heappop(heap)
    if i == n - 1 and j == m - 1:
        print(wall)
        break
    for di, dj in dir:
        ni, nj = i + di, j + dj
        if ni >= n or ni < 0 or nj >= m or nj < 0 or visited[ni][nj]:
            continue
        visited[ni][nj] = True
        if maze[ni][nj]:
            heapq.heappush(heap, (wall + 1, ni, nj))
        else:
            heapq.heappush(heap, (wall, ni, nj))