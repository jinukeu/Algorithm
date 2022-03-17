from sys import stdin
from collections import deque
n = int(input())

idx = 0
stack = deque()
for _ in range(n):
      command = list(stdin.readline().split())
      if command[0] == 'push_front':
            stack.appendleft(int(command[1]))
      if command[0] == 'push_back':
            stack.append(int(command[1]))
      if command[0] == 'pop_front':
            if len(stack) != 0:
                  print(stack.popleft())
            else:
                  print(-1)
      if command[0] == 'pop_back':
            if len(stack) != 0:
                  print(stack.pop())
            else:
                  print(-1)
      if command[0] == 'size':
            print(len(stack))
      if command[0] == 'empty':
            if len(stack) != 0:
                  print(0)
            else:
                  print(1)
      if command[0] == 'front':
            if len(stack) != 0:
                  print(stack[0])
            else:
                  print(-1)
      
      if command[0] == 'back':
            if len(stack) != 0:
                  print(stack[len(stack) - 1])
            else:
                  print(-1)

