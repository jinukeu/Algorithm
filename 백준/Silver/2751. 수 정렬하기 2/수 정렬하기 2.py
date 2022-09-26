from sys import stdin

n = int(input())
arr = [0] * n
for i in range(n):
  arr[i] = (int(stdin.readline()))

arr.sort()
for i in arr:
  print(i)