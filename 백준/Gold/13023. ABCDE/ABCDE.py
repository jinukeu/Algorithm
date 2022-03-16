def dfs(f, friends, visited, cnt):
    if cnt == 4:
        print(1)
        exit()
    for friend in friends[f]:
        if visited[friend]: continue
        visited[friend] = True
        dfs(friend, friends, visited, cnt + 1)
        visited[friend] = False

n, m = map(int, input().split())
friends = [[] for _ in range(n)]

for _ in range(m):
    a, b = map(int, input().split())
    friends[a].append(b)
    friends[b].append(a)

for i in range(n):
    visited = [False] * n
    visited[i] = True
    dfs(i, friends, visited, 0)
    visited[i] = False

print(0)