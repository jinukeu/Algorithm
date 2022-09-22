def solution(genres, plays):
    answer = []
    genresPlayNum = dict()
    for i in range(len(genres)):
        if(genresPlayNum.get(genres[i]) == None): genresPlayNum[genres[i]] = [[plays[i], i]]
        else: genresPlayNum[genres[i]].append([plays[i], i])
    genresPlaySum = dict()
    for gen in genres:
        s = 0
        for (num, i) in genresPlayNum[gen]:
            s += num
        genresPlaySum[gen] = s
    sortedDic = sorted(genresPlaySum.items(), key = lambda item: item[1], reverse=True)
    print(sortedDic)
    for k, v in sortedDic:
        sortedGPN = sorted(genresPlayNum[k], key=lambda x: (-x[0], x[1]))
        for (a, b) in sortedGPN[:2]:
            answer.append(b)
    return answer