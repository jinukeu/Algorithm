def solution(triangle):
    answer = 0
    m = [[0 for col in range(row + 1)] for row in range(len(triangle))]
    
    m[0] = triangle[0]
    
    for i in range(1, len(triangle)):
        for j in range(0, i + 1):
            for d in [0, -1]:
                nj = d + j
                if(0 <= nj <= i - 1):
                    m[i][j] = max(m[i][j], triangle[i][j] + m[i - 1][nj])
                    
    answer = (max(m[len(m) - 1]))
    
    return answer