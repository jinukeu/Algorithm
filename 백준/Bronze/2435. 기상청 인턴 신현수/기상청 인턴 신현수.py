n, k = map(int, input().split())
lists = list(map(int, input().split()))
sums = [0]
s = 0
for li in lists:
    s += li
    sums.append(s)

answer = sums[k] - sums[0]
for i in range(1, n - k + 1):
    if answer < sums[k + i] - sums[i]:
        answer = sums[k + i] - sums[i]

print(answer)