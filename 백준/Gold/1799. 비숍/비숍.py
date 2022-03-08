import sys
input = sys.stdin.readline

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
dig1 = [False] * (n * 2)
dig2 = [False] * (n * 2)

black = []
white = []

for i in range(n):
    for j in range(n):
        if board[i][j] == 1:
            if (i + j + 1) % 2 != 0:
                black.append((i, j))
            else:
                white.append((i, j))

def backtracking(bishop, index, cnt, isBlack):
    global blackCnt, whiteCnt
    if index == len(bishop):
        if isBlack:
            blackCnt = max(blackCnt, cnt)
        else:
            whiteCnt = max(whiteCnt, cnt)
        return
    i, j = bishop[index]
    if dig1[i + j + 1] or dig2[j - i + n]:
        backtracking(bishop, index + 1, cnt, isBlack)
    else:
        dig1[i + j + 1] = True
        dig2[j - i + n] = True
        backtracking(bishop, index + 1, cnt + 1, isBlack)
        dig1[i + j + 1] = False
        dig2[j - i + n] = False
        backtracking(bishop, index + 1, cnt, isBlack)

blackCnt = whiteCnt = 0
backtracking(black, 0, 0, True)
backtracking(white, 0, 0, False)

print(blackCnt + whiteCnt)