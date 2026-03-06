'''
    1 ....
        _ _ _ _ _ _ 

        0000
        0100
        101
'''


class Solution:
    def checkOnesSegment(self, s: str) -> bool:
        return '01' not in s
