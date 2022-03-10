n,m = map(int,input().split())
trees = list(map(int,input().split()))

low = 0
high = max(trees)
mid = (high+low)//2

while high >= low:
    sum = 0
    for tree in trees:
        if tree > mid:
            sum += tree - mid
    
    if sum < m: #너무 높게 자름
        high = mid - 1
    else:
        low = mid + 1

    mid = (low + high)//2


print(mid)