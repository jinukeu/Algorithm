import sys, itertools
from collections import deque
input = sys.stdin.readline

a = list(input().rstrip())
ra = list(reversed(a))
T = deque(list(input().rstrip()))
censor = True
front, back = [], []
while censor:
    censor = False
    
    while T:
        front.append(T.popleft())
        if len(front) >= len(a):
            if front[len(front) - len(a):] == a:
                for _ in range(len(a)):
                    front.pop()
                censor = True
                break
            
    if not censor:
        while back:
            front.append(back.pop())
            if len(front) >= len(a):
                if front[len(front) - len(a):] == a:
                    for _ in range(len(a)):
                        front.pop()
                    censor = True
                    break
    if not censor:
        break
    
    while T:
        back.append(T.pop())
        if len(back) >= len(a):
            if back[len(back) - len(a):] == ra:
            #if list(itertools.islice(back,len(back) - len(a), len(back))) == ra:
                for _ in range(len(a)): back.pop()
                censor = True
                break
            
    if not censor:
        while front:
            back.append(front.pop())
        if len(back) >= len(a):
            if back[len(back) - len(a):] == ra:
            #if list(itertools.islice(back,len(back) - len(a), len(back))) == ra:
                for _ in range(len(a)): back.pop()
                censor = True
                break
    if not censor:
        break
    
    
back = list(back)
print("".join(front + back[:-1]))