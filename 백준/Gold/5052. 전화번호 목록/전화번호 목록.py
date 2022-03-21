import sys
input = sys.stdin.readline

def insert(phoneNum):
    global trie
    curNode = trie
    for num in phoneNum:
        if '*' in curNode:
            return False
        if num not in curNode:
            curNode[num] = {}
        curNode = curNode[num]
    curNode['*'] = True
    return True

t = int(input())

for _ in range(t):
    n = int(input())
    phoneNums = [list(input().rstrip()) for _ in range(n)]
    phoneNums.sort(key = lambda x:len(x))
    ans = "YES"
    trie = {}
    for phoneNum in phoneNums:
        if not insert(phoneNum):
            ans = "NO"
            break
    print(ans)