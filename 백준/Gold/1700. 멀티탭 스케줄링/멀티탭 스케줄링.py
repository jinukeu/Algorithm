import sys
input = sys.stdin.readline

def laterUse(app, electricAppList, i):
    for j in range(i + 1, len(electricAppList)):
        if electricAppList[j] == app:
            return j
    return -1

n, k = map(int, input().split())
electricAppList = list(map(int, input().split()))
multTapList = [0] * n
keep = [-1] * n
cnt = 0

for appIndex in range(k):
    # Debug
    # print(electricAppList[appIndex])
    # print("multTap", multTapList)
    # print("keep", keep)
    # print("---")
    # 이미 멀티탭에 연결되어 있는 경우
    if electricAppList[appIndex] in multTapList:
        keep[multTapList.index(electricAppList[appIndex])] = laterUse(electricAppList[appIndex], electricAppList, appIndex)
        continue
    for i in range(n):
        # 빈 멀티탭이 있는 경우
        if 0 in multTapList:
            if multTapList[i] == 0:
                multTapList[i] = electricAppList[appIndex]
                # 나중에 같은 전자기기를 사용하는 경우
                keep[i] = laterUse(electricAppList[appIndex], electricAppList, appIndex)
                break
        # 멀티탭을 전부 유지해야 하는 경우
        elif -1 not in keep:
            unPlug = keep.index(max(keep))
            multTapList[unPlug] = electricAppList[appIndex]
            keep[unPlug] = laterUse(electricAppList[appIndex], electricAppList, appIndex)
            cnt += 1
            break
        # 빈 멀티탭이 없는 경우
        else:
            if keep[i] == -1:
                multTapList[i] = electricAppList[appIndex]
                # 나중에 같은 전자기기를 사용하는 경우
                keep[i] = laterUse(electricAppList[appIndex], electricAppList, appIndex)
                cnt += 1
                break

print(cnt)