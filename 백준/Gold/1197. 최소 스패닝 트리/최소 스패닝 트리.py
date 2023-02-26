def findParent(parent, x):
    if x != parent[x]:
        parent[x] = findParent(parent, parent[x])
    return parent[x]

def union(parent, a, b):
    a = findParent(parent, a)
    b = findParent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b
        
v, e = map(int,input().split())
parent = [0] * (v + 1)

for i in range(1, v + 1):
    parent[i] = i

edges = []
result = 0

for _ in range(e):
    a, b, c = map(int,input().split())
    edges.append((c, a, b))

edges.sort()

for edge in edges:
    cost, a, b = edge
    if findParent(parent, a) != findParent(parent, b):
        union(parent, a, b)
        result += cost

print(result)