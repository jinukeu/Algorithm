import sys
input = sys.stdin.readline

n = int(input())
arr = [int(input()) for _ in range(n)]
stk, ans = [], []
insertNum = 1
for a in arr:
    while True:   
        if insertNum <= a:
            stk.append(insertNum)
            insertNum += 1
            ans.append('+')
            popNum = -1
        elif insertNum > a:
            try:
                popNum = stk.pop()
            except:
                print("NO")
                exit()
            ans.append('-')
        if popNum == a:
            break

for a in ans:
    print(a)