from collections import deque

def solution(priorities, location):
    answer = 0
    que = deque(priorities)
    while True:
        if max(que) == que[0]:
            answer += 1
            if location == 0:
                break
            que.popleft()
            location -= 1
        if que[0] != max(que):
            q = que.popleft()
            que.append(q)
            if location == 0:
                location = len(que) - 1
            else:
                location -= 1
    return answer