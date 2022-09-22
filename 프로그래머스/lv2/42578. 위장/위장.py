def solution(clothes):
    answer = 1
    dic = dict()
    for (value, key) in clothes:
        if(dic.get(key) == None): dic[key] = 1
        else: dic[key] += 1
    for k in dic.keys():
        answer *= (dic[k] + 1)
    return answer - 1