def find(lans, n,h,m):
    high = h
    min = m
    mid = (high + min)//2

    while min <= high:
        sum = 0
        for lan in lans:
            sum += lan//mid

        if sum >= n:
            min = mid + 1
        elif sum < n:
            high = mid - 1
    

        mid = (high + min)//2
    
    return mid


from sys import stdin
k, n = map(int,stdin.readline().split())
lans = []


for i in range(k):
    lans.append(int(input()))

high = max(lans)
min = 1

mid1 = find(lans,n,high,min)

print(mid1)