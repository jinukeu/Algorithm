import sys
sys.setrecursionlimit(10**6)

def createSub(FolderDirectory, FileDirectory, f):
    if f not in FolderDirectory:
        FolderDirectory[f] = [f]
    if f not in FileDirectory:
        FileDirectory[f] = []

def createRoot(FolderDirectory, FileDirectory, p):
    if p not in FolderDirectory:
        FolderDirectory[p] = [p]
    if p not in FileDirectory:
        FileDirectory[p] = []
        
def dfs(query, FolderDirectory):
    global type, cnt
    for FolderName in FolderDirectory[query]:
        if query == FolderName:
            for fileName in FileDirectory[FolderName]:
                type.add(fileName)
                cnt += 1
        if query != FolderName:
            dfs(FolderName, FolderDirectory)

n, m = map(int, input().split())
FolderDirectory, FileDirectory = dict(), dict()

for _ in range(n + m):
    p, f, c = input().split()
    if int(c):
        createRoot(FolderDirectory, FileDirectory, p)
        createSub(FolderDirectory, FileDirectory, f)
        FolderDirectory[p].append(f)
    else:
        createRoot(FolderDirectory, FileDirectory, p)
        FileDirectory[p].append(f)
        
q = int(input())
queryList = [input().split('/')[-1] for _ in range(q)]

for query in queryList:
    type = set()
    cnt = 0
    dfs(query, FolderDirectory)
    print(len(type), cnt)