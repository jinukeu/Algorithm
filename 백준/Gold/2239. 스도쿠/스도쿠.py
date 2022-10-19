import sys
input = sys.stdin.readline

arr = [list(map(int, input().rstrip())) for _ in range(9)]

def check(col, n):
    for a in arr:
        if a[col] == n:
            return True
    return False

def check33(r, c, i):
    for rowLeft in range(0, 7, 3):
            for colLeft in range(0, 7, 3):
                    if rowLeft <= r <= rowLeft + 2 and colLeft <= c <= colLeft + 2:
                        check = arr[rowLeft][colLeft:colLeft + 3] + arr[rowLeft + 1][colLeft:colLeft + 3] + arr[rowLeft + 2][colLeft:colLeft + 3]
                        if i in check:
                            return True
                        else:
                            return False
            

def recursion(r, c):
    if r > 8 or c > 8:
        for a in arr:
            print("".join(map(str, a)))
        exit()
    if arr[r][c] != 0:
        if c == 8:
            recursion(r + 1, 0)
        else:
            recursion(r, c + 1)
        return
    
    for i in range(1, 10):
        if i in arr[r] or check(c, i) or check33(r, c, i):
            continue
        arr[r][c] = i
        if c == 8:
            recursion(r + 1, 0)
        else:
            recursion(r, c + 1)
        arr[r][c] = 0

recursion(0 , 0)