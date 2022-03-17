def removeBlock(targetHeight, grounds, holdBlockNum):
    time = 0
    for ground in grounds:
        for g in ground:
            if g > targetHeight:
                time += (g - targetHeight) * 2
                holdBlockNum += (g - targetHeight)
    return time, holdBlockNum
    
def inputBlock(targetHeight, grounds, holdBlockNum):
    time = 0
    for ground in grounds:
        for g in ground:
            if g < targetHeight:
                time += (targetHeight - g)
                holdBlockNum -= (targetHeight - g)
    if holdBlockNum < 0:
        return 1e9
    else:
        return time

n, m, holdBlockNum = map(int, input().split())
grounds = [list(map(int, input().split())) for _ in range(n)]
minHeight = 256
maxHeight = 0
ans = [1e9, 0]

for ground in grounds:
    minHeight = min(min(ground), minHeight)
    maxHeight = max(max(ground), maxHeight)

for targetHeight in range(minHeight, maxHeight + 1):
    tmpHoldBlockNum = holdBlockNum
    # 블럭 제거
    time, tmpHoldBlockNum = removeBlock(targetHeight, grounds, tmpHoldBlockNum)
    # 블럭 쌓기
    time += inputBlock(targetHeight, grounds, tmpHoldBlockNum)
    
    if time < ans[0]:
        ans = [time, targetHeight]
    elif time == ans[0] and targetHeight > ans[1]:
        ans = [time, targetHeight]

print(*ans)