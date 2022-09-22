def solution(participant, completion):
    answer = ''
    d = dict()
    for com in completion:
        if d.get(com) == None:
            d[com] = 1
        else:
            d[com] = d[com] + 1
    for part in participant:
        if d.get(part) == None or d.get(part) == 0:
            answer = part
        else:
            d[part] = d[part] - 1
    return answer
