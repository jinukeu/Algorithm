from collections import deque
import math

def solution(progresses, speeds):
    answer = []
    que = deque()
    for i in range(len(speeds)):
        que.append(math.ceil((100 - progresses[i]) / speeds[i]))
    while(que):
        first = que.popleft()
        cnt = 1
        while(que):
            if(first >= que[0]):
                cnt += 1
                que.popleft()
            else:
                break
        answer.append(cnt)
    return answer