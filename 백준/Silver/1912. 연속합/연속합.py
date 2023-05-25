n = int(input())
arr = list(map(int, input().split()))
st = ed = ns = nd = 0
ans = dp = arr[0]

for i in range(1, len(arr)):
    if arr[i] + dp > ans:
        ans = arr[i] + dp
        dp = ans
        nd = i
        st = ns
        ed = nd  
        continue
    
    if arr[i] > ans:
        ans = dp = arr[i]
        st = ed = i
        continue
    
    if arr[i] + dp < 0:
        dp = 0
        ns = nd = i + 1
        continue
        
    if arr[i] + dp < ans:
        dp += arr[i]
        nd = i     
         
print(sum(arr[st:ed + 1]))