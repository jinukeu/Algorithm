import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
dp = [-1e10]
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
    if arr[i] > dp[-1]:
        dp.append(arr[i])
        dp2[i] = len(dp) - 1
    else:
        index = binary(0, len(dp) - 1, arr[i])
        dp2[i] = index
        dp[index] = arr[i]

print(len(dp) - 1)

i = n
find = len(dp) - 1
ans = []
while find != 0:
    for j in range(i, -1, -1):
        if dp2[j] == find:
            ans.append(arr[j])
            i = j
            find -= 1
            break

ans.reverse()
print(" ".join(map(str, ans)))