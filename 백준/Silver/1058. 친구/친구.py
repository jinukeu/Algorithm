n = int(input())
arr = [[0] * n for _ in range(n)]

for i in range(n):
    tmp = input()
    for j in range(n):
        if tmp[j] == 'N':
            arr[i][j] = 0
        else:
            arr[i][j] = 1
            
ans = 0
for i in range(n):
    distance = set()
    for j in range(n):
        for k in range(n):
            if arr[i][k] == arr[k][j] == 1:
                if i == j:
                    distance.add((i, k))
                else:
                    distance.add((i, j))
        if ans <len(distance):
            ans = len(distance)
        
print(ans)