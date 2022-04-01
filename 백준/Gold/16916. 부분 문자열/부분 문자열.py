def Ktable(pattern):
    lp = len(pattern)
    tb = [0 for _ in range(lp)]
    
    pidx = 0
    for idx in range(1, lp):
        while pidx > 0 and pattern[idx] != pattern[pidx]:
            pidx = tb[pidx-1]
        
        if pattern[idx] == pattern[pidx] :
            pidx += 1
            tb[idx] = pidx
    
    return tb

def KMP(word, pattern):
    table = Ktable(pattern)

    pidx = 0
    
    for idx in range(len(word)):
        while pidx > 0 and word[idx] != pattern[pidx] :
            pidx = table[pidx-1]
        if word[idx] == pattern[pidx]:
            if pidx == len(pattern)-1 :
                print(1)
                return
            else:
                pidx += 1
    print(0)

p = input()
s = input()
KMP(p, s)