import heapq

def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville)
    while len(scoville) > 1:
        if(scoville[0] >= K):
            break
        f = heapq.heappop(scoville)
        s = heapq.heappop(scoville)
        heapq.heappush(scoville, f + s*2)
        answer += 1
    if(scoville[0] < K):
        answer = -1
    return answer