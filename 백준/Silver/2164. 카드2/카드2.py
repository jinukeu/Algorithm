n = int(input())
 
a = [i for i in range(1,n+1)]

i = 0
j = 0
for i in range(1,n):
    a.append(a[i + j])
    j += 1
 
print(a[i+j])