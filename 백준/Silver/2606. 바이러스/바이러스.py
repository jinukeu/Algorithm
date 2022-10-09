from collections import deque

comNum = int(input())
connect = int(input())

arrays = [list(map(int, input().split())) for _ in range(connect)]
answer = [1]

queue = deque()

for array in arrays:
    if 1 in array:
        queue.append(array[0])
        queue.append(array[1])
        array[0] = array[1] = 0
        break

while queue:
    find = queue.popleft()
    if find == 0:
        continue
    answer.append(find)
    for array in arrays:
        if find in array:
            for idx in range(2):
                if array[idx] not in queue:
                    queue.append(array[idx])
                    array[idx] = 0
                else:
                    array[idx] = 0

print(len(set(answer)) - 1)
                
