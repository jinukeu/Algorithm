import heapq

t = int(input())

# maxHeap Mid minHeap
for _ in range(t):
    n = int(input())
    minHeap, maxHeap = [], []
    ans = []
    cnt = 0
    while n != cnt:
        arr = list(map(int, input().split()))
        for i in range(len(arr)):
            if cnt == 0:
                minHeap.append(arr[i])
                ans.append(arr[i])
            elif cnt == 1:
                maxHeap.append(-arr[i])
            elif cnt == 2:
                tmp = [arr[i], minHeap.pop(), -maxHeap.pop()]
                tmp.sort()
                minHeap.append(tmp[2])
                maxHeap.append(-tmp[0])
                mid = tmp[1]
                ans.append(mid)
            elif (i + 1) % 2 == 0:
                tmp = [arr[i], mid, heapq.heappop(minHeap), -heapq.heappop(maxHeap)]
                tmp.sort()
                heapq.heappush(minHeap, tmp[3])
                heapq.heappush(minHeap, tmp[2])
                heapq.heappush(maxHeap, -tmp[1])
                heapq.heappush(maxHeap, -tmp[0])
            else:
                tmp = [arr[i], heapq.heappop(minHeap), -heapq.heappop(maxHeap)]
                tmp.sort()
                heapq.heappush(minHeap, tmp[2])
                heapq.heappush(maxHeap, -tmp[0])
                mid = tmp[1]
                ans.append(mid)
            cnt += 1
    print(len(ans))
    for i in range(0, n, 10):
        if not len(ans[i:i + 10]):
            break
        print(*ans[i:i + 10])