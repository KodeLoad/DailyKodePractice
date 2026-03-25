'''
    1   2   3   4   1

BF:         O(n ** 2)
sort:       O(n * log n)
set:        O(n) TC / O(n) SC

math(adv): 
    1   2   99  1000    -1
'''

class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        return len(nums) != len(set(nums))