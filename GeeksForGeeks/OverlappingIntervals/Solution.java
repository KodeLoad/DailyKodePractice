package GeeksForGeeks.OverlappingIntervals;

/*
    Greedy algorithms
    
        to take the perceved correct route
            which would make next steps clear
            
    [[1, 3], [2, 4], [6, 8], [9, 10]]
    vs
    [[6, 8], [4, 7], [2, 3], [1, 9]]
    [4, 8], [2, 3], [1, 9] :: single parse is 
                                not fully correct
                                
    we need to understand what should come first?
    it what interval starts first!
    
    
    *--------* :: last
    
    *--------*         // c1 inc
        *--------*     // c2 inc
      *--*             // c3 inc

    last.end >= incoming.start
    
    
    *-----*
       *--------*
    *-----------*
*/
class Solution {
    public ArrayList<int[]> mergeOverlap(int[][] a) {
        ArrayList<int[]> res = new ArrayList<>();
        // make the input interval sorted
        // first starting would be first in array
        Arrays.sort(a, (_i, _j) -> _i[0] - _j[0]);
        
        // consider if we can merge the intervals 
        // one by one
        int[] last = a[0].clone();
        for (int i = 1; i < a.length; i++) {
            // check if merging?
            if (last[1] >= a[i][0]) {
                // merging 
                last[1] = Math.max(last[1], a[i][1]);
                // we did a merge
                continue;
            }
            
            // didnt intersect?
            res.add(last);
            last = a[i].clone();
        }
        
        res.add(last);
        
        // return result
        return res;
    }
}