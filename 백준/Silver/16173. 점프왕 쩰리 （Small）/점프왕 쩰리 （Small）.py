from collections import deque

def bfs(n, arr, queue):
    i = j = 0
    queue.append([arr[i][j], i, j])
    arr[i][j] = -2
    while queue:
        jump = queue.popleft()
        if jump[0] == -1:
            print("HaruHaru")
            return
        i,j = jump[1], jump[2]
        if i + jump[0] < n:
            if arr[i + jump[0]][j] != -2:
                queue.append([arr[i + jump[0]][j], jump[0] + i, j])
                arr[i + jump[0]][j] = -2
        if j + jump[0] < n:
            if arr[i][j + jump[0]] != -2:
                queue.append([arr[i][j + jump[0]], i, jump[0] + j])
                arr[i][j + jump[0]] = -2                  
    print("Hing")
    return

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
queue = deque()

bfs(n, arr, queue)