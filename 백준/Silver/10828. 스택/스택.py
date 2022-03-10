from sys import stdin
n = int(input())

idx = 0
stack = []
for _ in range(n):
      command = list(stdin.readline().split())
      if command[0] == 'push':
            stack.append(int(command[1]))
      if command[0] == 'pop':
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
      if command[0] == 'top':
            if len(stack) != 0:
                  print(stack[len(stack) - 1])
            else:
                  print(-1)