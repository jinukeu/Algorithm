arr = list(input())
bomb = list(input())
stk = []
for i in range(len(arr)):
    stk.append(arr[i])
    if len(stk) >= len(bomb):
        if stk[len(stk) - len(bomb):] == bomb:
            for _ in range(len(bomb)):
                stk.pop()

if len(stk) > 0:
    print("".join(stk))
else:
    print("FRULA")