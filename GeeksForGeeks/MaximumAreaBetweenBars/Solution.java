/*
    i, j :: keep track of area
    TC: O(n**2) 

 ---------
    if they are far then it is good
    
    (5) 5 5 5 (5)
    
    what if we choose the terminals first
    
    l <-----> r
        l   r
        
    we need to shrink this l and r
    
    l && r
    max area:
        bars are max
        remove the bars which are less
        
    TC: O(n)
*/

import java.util.List;

class Solution {
    public int maxArea(List<Integer> a) {
        int l = 0, r = a.size() - 1;
        int res = 0;

        while (l < r) {
            int minBarLength;
            int dis = r - l - 1;
            if (a.get(l) < a.get(r)) {
                minBarLength = a.get(l);
                l++;
            } else {
                minBarLength = a.get(r);
                r--;
            }

            int curArea = minBarLength * dis;
            res = Math.max(res, curArea);
        }

        return res;
    }
}
