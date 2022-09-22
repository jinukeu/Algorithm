def solution(phone_book):
    answer = True
    dic = dict()
    for phone in phone_book:
        for i in range(len(phone) - 1):
            dic[phone[:i + 1]] = True
    for phone in phone_book:
        if(dic.get(phone)): 
            answer = False
    return answer