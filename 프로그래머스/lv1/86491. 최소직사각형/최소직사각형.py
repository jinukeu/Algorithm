def solution(sizes):
    answer = 0
    for i in range(len(sizes)):
        sizes[i].sort()
    maxW = 0
    maxH = 0
    for w, h in sizes:
        if w> maxW: maxW = w
        if h > maxH: maxH = h
    answer = maxH * maxW
    return answer