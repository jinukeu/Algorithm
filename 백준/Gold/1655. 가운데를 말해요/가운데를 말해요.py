import heapq, sys
input = sys.stdin.readline

minHeap, maxHeap = [], []
#maxHeap < minHeap

n = int(input())
for i in range(1, n + 1):
    num = int(input())
    if i == 1:
        mid = num
        print(mid)
    elif i == 2:
        print(min(mid, num))
        heapq.heappush(minHeap, max(mid, num))
        heapq.heappush(maxHeap, -min(mid, num))
    elif i % 2 == 1:
        tmp = [num, heapq.heappop(minHeap), -heapq.heappop(maxHeap)]
        tmp.sort()
        print(tmp[1])
        mid = tmp[1]
        heapq.heappush(minHeap, tmp[2])
        heapq.heappush(maxHeap, -tmp[0])
    else:
        tmp = [num, heapq.heappop(minHeap), -heapq.heappop(maxHeap), mid]
        tmp.sort()
        print(tmp[1])
        heapq.heappush(minHeap, tmp[2])
        heapq.heappush(minHeap, tmp[3])
        heapq.heappush(maxHeap, -tmp[0])
        heapq.heappush(maxHeap, -tmp[1])
            