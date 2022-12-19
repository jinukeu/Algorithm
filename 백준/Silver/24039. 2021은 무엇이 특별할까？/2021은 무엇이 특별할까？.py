import sys
input = sys.stdin.readline

n = int(input())

def getNext(a):
    a += 1
    while True:
        flag = True
        for i in range(2, a):
            if a % i == 0:
                flag = False
        if flag:
            return a
        else:
            a += 1

prev = 2
while True:
    if prev * getNext(prev) > n:
        print(prev * getNext(prev))
        break
    prev = getNext(prev)