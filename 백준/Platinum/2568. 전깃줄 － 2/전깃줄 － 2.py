import sys
input = sys.stdin.readline

n = int(input())
arr = []
for _ in range(n):
    a, b = map(int, input().split())
    arr.append((b, a))
arr.sort()
dp = [0]
dp2 = [0] * (n + 1)

def binary(start, end, find):
    while start <= end:
        mid = (start + end) // 2
        if dp[mid] > find:
            end = mid - 1
        elif dp[mid] < find:
            start = mid + 1
        else:
            return mid
    return start

for i in range(n):
    if arr[i][1] > dp[-1]:
        dp.append(arr[i][1])
        dp2[i] = len(dp) - 1
    else:
        index = binary(0, len(dp) - 1, arr[i][1])
        dp2[i] = index
        dp[index] = arr[i][1]

print(n - len(dp) + 1)
i = n
find = len(dp) - 1
ans = []
while find != 0:
    for j in range(i, -1, -1):
        if dp2[j] == find:
            ans.append(arr[j][1])
            i = j
            find -= 1
            break

ans.reverse()
i = 0
arr.sort(key=lambda x:x[1])
for a in arr:
    if i >= len(ans):
        print(a[1])
        continue
    if a[1] == ans[i]:
        i += 1
        continue
    print(a[1])
