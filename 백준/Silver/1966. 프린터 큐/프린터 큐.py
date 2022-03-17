# 가장 뒤에 재배치
# print(a[0])
# a.append(a[0])
# a = a[1:]
# print(a[0])

#출력
#a = a[1:]

r = int(input())
ans = []

for _ in range(r):
    n,m = map(int,input().split())
    arr = list(map(int,input().split()))
    index = 0
    count = 0
    while True:
        ma = max(arr[index:])
        if ma != arr[index]:
            arr.append(arr[index])
            if m == index:
                m = len(arr) - 1
            index += 1
        else:
            count += 1
            if m == index:
                break
            index += 1

    ans.append(count)

for i in ans:
    print(i)
     