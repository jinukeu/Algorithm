import sys
input = sys.stdin.readline

n = int(input())
weight = list(map(int, input().split()))
weight.sort()

s = 0
for w in weight:
    if 2 * s + 1 < s + w:
        print(s + 1)
        exit()
    s += w
    
print(s + 1)