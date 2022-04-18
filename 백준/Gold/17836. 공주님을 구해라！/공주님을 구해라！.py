from collections import deque

n, m, t = map(int, input().split()) #n 세로 m 가로
arr = []
visited = [[[False] * 2 for _ in range(m)] for _ in range(n)]

for _ in range(n):
      arr.append(list(map(int, input().split())))

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

queue = deque()
queue.append([0,0,0,0])
visited[0][0][0] = True
ans = []

while queue:
      q = queue.popleft()
      i, j, gram, cnt = q[0], q[1], q[2], q[3]
      if i == n - 1 and j == m - 1:
            ans.append(cnt)
      for idx in range(4):
            ni = i + dy[idx]
            nj = j + dx[idx]
            if ni < 0 or ni >= n or nj < 0 or nj >= m:
                  continue
            if not visited[ni][nj][gram]:
                  visited[ni][nj][gram] = True
                  if gram == 1:
                        queue.append([ni, nj, gram, cnt + 1])
                  elif arr[ni][nj] != 1:
                        if arr[ni][nj] == 2:
                              ng = 1
                        else:
                              ng = 0
                        queue.append([ni, nj, ng, cnt + 1])

try:
      if min(ans) > t:
            print("Fail")
      else:
            print(min(ans))
except:
      print("Fail")