package GeeksForGeeks.MinimumNumberOfWorkers;

/*
    [1, 2, 1, 0]
    n 
        can / cannot
    
    2 ** n
    
    ----
    
    0 -> 1 -> 2 -> ...
    // min.
    1 -> 
    
    ----
    
    0   1   2   3
    1   2   1   0
    *   *
    -   -   -   -
    
    shall i choose 0 or 1 (index)
    
    I can seperate them into intervals'
    
    multiple people to solve to problem
    [----]
    
    0  1  2         5  6   7
    2, 3, 4, -1, 2, 0, 0, -1, 0
    
    [0, 6] ::
    [7, 8] :: c
    
    0 -> n
    everyone to start early
*/

class Solution {
    public int minMen(int a[]) {
        List<int[]> intervals = new ArrayList<>();
        
        // fill the interval
        for (int i = 0; i < a.length; i++) {
            if (a[i] == -1) continue;
            
            intervals.add(new int[] {
                Math.max(0, i - a[i]),
                Math.min(a.length - 1, i + a[i])
            });
        }
        
        // sort
        intervals.sort(
            (_i, _j) -> (_i[0] - _j[0])
        );
        
        
        int cnt = 0;
        int farthest = -1; // if == a.length - 1
        int i = 0;
        
        while (farthest < a.length - 1) {
            // try making me farthest again
            int far = farthest;
            
            // i should  atleast be in begining of the interval
            while (
                i < intervals.size() &&
                intervals.get(i)[0] <= farthest + 1
            ) {
                // extending the span
                far = Math.max(far, intervals.get(i)[1]);
                i++;
            }
            
            // I've to check my farthest and update it
            if (far <= farthest) {
                return -1;
            }
            
            farthest = far;
            cnt++;
        }
        
        return cnt;
    }
}
