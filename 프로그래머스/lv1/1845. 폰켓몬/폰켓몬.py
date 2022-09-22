def solution(nums):
    answer = 0
    cnt = len(nums) // 2
    nums = set(nums)
    answer = min(len(nums), cnt)
    return answer