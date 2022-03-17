n, m = map(int, input().split())
arr = [False] * (m + 1)

for i in range(2, m + 1):
      if i * i > m:
            break

      if arr[i]:
            continue

      for j in range(i * 2 , m + 1, i):
            if not arr[j] and j % i == 0:
                  arr[j] = True


for i in range(n, m + 1):
      if not arr[i] and i > 1:
            print(i)