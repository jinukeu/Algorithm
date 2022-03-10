from sys import stdin
stk = []
n = int(stdin.readline())

for _ in range(n):
      a = int(stdin.readline())
      if a == 0:
            stk.pop()
      else:
            stk.append(a)

print(sum(stk))