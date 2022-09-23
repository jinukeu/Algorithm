from itertools import permutations

def solution(numbers):
    answer = 0
    numbers = list(numbers)
    per = []
    nl = set()
    for i in range(1, len(numbers) + 1):
        per += list(permutations(numbers, i))
    for p in per:
        n = (int(''.join(s for s in p)))
        flag = False
        if n < 2: continue
        for j in range(2, n):
            if(n % j == 0):
                flag = True
                break
        if flag == False:
            nl.add(n)
    
    answer = len(nl) 
        
    return answer