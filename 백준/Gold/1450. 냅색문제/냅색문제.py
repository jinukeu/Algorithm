import sys, math
from itertools import combinations
input = sys.stdin.readline

def binary(arr, find):
    start = 0
    end = len(arr)
    while start < end:
        mid = (start + end) // 2
        if arr[mid] > find:
            end = mid
        else:
            start = mid + 1
    return start
            

n, c = map(int, input().split())
items = list(map(int, input().split()))

left = items[n//2:]
right = items[:n//2]

leftCombi = []
rightCombi = []

for i in range(1, len(left) + 1):
    for a in combinations(left, i):
        leftCombi.append(sum(a))

for i in range(1, len(right) + 1):
    for a in combinations(right, i):
        rightCombi.append(sum(a))

ans = 1
leftCombi.sort()
rightCombi.sort()

ans += binary(leftCombi, c) + binary(rightCombi, c)

for l in leftCombi:
    ans += binary(rightCombi, c - l)

print(ans)