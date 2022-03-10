n = int(input())
arr = []

for _ in range(n):
    arr.append(int(input()))

print(round(sum(arr)/n))
arr.sort()
print(arr[n//2])
b = list(set(arr))
b.sort()


count = [0 for _ in range(len(b))]

for i in range(len(b)):
    for a in arr:
        if a == b[i]:
            count[i] += 1


maxi = count.index(max(count))
maxv = []
for i in range(len(count)):
    if count[i] == max(count):
        maxv.append(b[i])

maxv.sort()

if len(maxv) > 1:
    print(maxv[1])
else:
    print(maxv[0])

print(max(arr) - min(arr))