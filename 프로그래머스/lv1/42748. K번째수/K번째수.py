def solution(array, commands):
    answer = []
    for (i, j, k) in commands:
        newArr = array[i - 1:j]
        newArr.sort()
        answer.append(newArr[k - 1])
    return answer