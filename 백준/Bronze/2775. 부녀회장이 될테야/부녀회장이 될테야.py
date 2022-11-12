from sys import stdin
i = int(stdin.readline())
answer = []

for j in range(i):
      k = int(input())
      n = int(input())
      arr = [x for x in range(1, n + 1)]
      for _ in range(k):
            for z in range(1, n):
                  arr[z] = sum(arr[z - 1:z + 1])
      answer.append(arr[n - 1])

for a in answer:
      print(a)