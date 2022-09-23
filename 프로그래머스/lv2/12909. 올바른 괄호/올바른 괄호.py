def solution(s):
    answer = True
    stk = []
    for b in s:
        if(b == ")"):
            if len(stk) == 0:
                return False
            stk.pop()
        else:
            stk.append(b)
            
    if len(stk) != 0:
        return False

    return True