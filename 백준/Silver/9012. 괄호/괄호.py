n = int(input())

ans = []
for _ in range(n):
    a = list(input())
    i = 0
    while True:
        try:
            if ( (a[i] == '(') & (a[i + 1] == ')') ):
                del a[i]
                del a[i]
                i = 0
            else:
                i += 1
        except:
            break
    
    if len(a) == 0:
        ans.append("YES")
    else:
        ans.append("NO")

for i in ans:
    print(i)