n, k = map(int, input().split())
tmp = k
arr = [str(x) for x in range(1, n + 1)]
answer = []
for _ in range(n - 1):
      answer.append(arr[k - 1])
      arr.remove(arr[k - 1])
      k += tmp - 1
      while k > len(arr):
            k -= len(arr)

answer.append(arr[0])
print("<" + ', '.join(answer)+">")