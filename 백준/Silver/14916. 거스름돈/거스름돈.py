n = int(input())
cnt = 0
while n >= 2:
    if n%5 == 0:
        n -= 5
    elif n%2 == 0:
        n -=2
    elif n >= 5:
        n -= 5
    else:
        n -= 2
    cnt+=1

if n != 0:
    cnt = -1

print(cnt)