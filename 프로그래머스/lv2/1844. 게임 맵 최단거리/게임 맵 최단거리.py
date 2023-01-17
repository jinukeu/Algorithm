import heapq

def solution(maps):
    answer = 1e9
    dirs = ((0,1),(1,0),(-1,0),(0,-1))
    n = len(maps)
    m = len(maps[0])
    
    visited = [[1e9 for _ in range(m)] for _ in range(n)]
    
    heap = []
    heapq.heappush(heap, (1, 0, 0))
    visited[0][0] = 1
    
    while heap:
        (count,x, y) = heapq.heappop(heap)
        if x == n -1 and y == m -1:
            answer = count
            break
        
        for dx, dy in dirs:
            newX = dx + x
            newY = dy + y
            
            if not (0 <= newX < n) or not (0 <= newY < m):
                continue
                
            if visited[newX][newY] <= count + 1 or maps[newX][newY] == 0:
                continue
            
            heapq.heappush(heap,(count + 1, newX, newY))
            visited[newX][newY] = count + 1
        
    if(answer == 1e9): answer = -1
    return answer