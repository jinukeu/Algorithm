n = int(input())

st = 1
ed = 2
s = 1
cnt = 1
while st <= n and ed <= n:
      if s == n:
        cnt += 1
        s -= st
        st += 1
      elif s < n:
        s += ed
        ed += 1
      else:
        s -= st
        st += 1

print(cnt)