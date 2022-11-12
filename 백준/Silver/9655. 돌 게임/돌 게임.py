n = int(input())
dp = [0] * 1001
dp[1] = dp[3] = 1
dp[2] = -1

for i in range(4, n + 1):
    dp[i] = -dp[i - 1]

if dp[n] == 1:
    print("SK")
else:
    print("CY")