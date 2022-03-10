r, c = map(int, input().split())
arr = [list(input()) for _ in range(r)]
d = dict()

for i in range(c):
    tmp = ""
    for j in range(r):
        tmp += arr[j][i]
    d[tmp] = 0

cnt = 0
while True:
    td = dict()
    for k in d.keys():
        if k[1:] in td:
            print(cnt)
            exit()
        else:
            td[k[1:]] = 0
    d = td
    cnt += 1