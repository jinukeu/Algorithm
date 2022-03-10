from sys import stdin

arr = []
while True:
      line = list(input())
      if len(line) > 0:
            if line[0] == '.' and len(line) == 1:
                  break
      arr.append(line)

ans = []

def ch(a):
      stk = []
      for s in a:
            if s == '(' or s == '[':
                  stk.append(s)
            elif s == ')':
                  if len(stk) > 0:
                        if stk.pop() != '(':
                              return False
                  else:
                        return False
            elif s == ']':
                  if len(stk) > 0:
                        if stk.pop() != '[':
                              return False
                  else:
                        return False
      if len(stk) != 0:
            return False
      return True


for a in arr:
      if ch(a):
            ans.append("yes")
      else:
            ans.append("no")

for i in ans:
      print(i)
      