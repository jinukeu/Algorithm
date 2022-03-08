n = int(input())
inCars, outCars = [], dict()
for i in range(2 * n):
    if i < n:
        inCars.append(input())
    else:
        outCars[input()] = i - n
cnt = 0
for i in range(1, n):
    for j in range(i):
        if outCars[inCars[j]] > outCars[inCars[i]]:
            cnt += 1
            break

print(cnt)