import sys
input = sys.stdin.readline

d = dict()
total = 0
while True:
    name = input().rstrip()
    if not name:
        break
    if name in d:
        d[name] += 1
    else:
        d[name] = 1
    total += 1

dlist = list(d.keys())
dlist.sort()

for name in dlist:
    print(name, end=" ")
    print("{:.4f}".format((d[name] / total) * 100))