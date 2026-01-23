package GeeksForGeeks.SumOfSubarrayRanges;

/*

    i -> j
    O(n**2)
    
    
    a sub array can contribute within overall result
    [a0, a1, a2, a3]
    
    how much a[i] is contirubting to the differnce
        max
        min
    
    (count * max) - (count * min)
    
*/
class Solution {
    public int subarrayRanges(int[] arr) {
        return maxContri(arr) - minContri(arr);
    }
    
    int maxContri(int[] a) {
        int n = a.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i <= n; i++) {
            while (
                !stack.isEmpty() &&
                (
                    i == n ||
                    a[stack.peek()] < a[i]
                )
            ) {
                // I'm going to remove from stack
                int index = stack.pop();
                
                int left = stack.isEmpty()? -1 : stack.peek();
                int right = i;
                
                int cnt = (right - index) * (index - left);
                res += cnt * a[index];
            }
            
            stack.push(i);
        }
        
        return res;
    }
    
    int minContri(int[] a) {
        int n = a.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i <= n; i++) {
            while (
                !stack.isEmpty() &&
                (
                    i == n ||
                    a[stack.peek()] > a[i]
                )
            ) {
                // I'm going to remove from stack
                int index = stack.pop();
                
                int left = stack.isEmpty()? -1 : stack.peek();
                int right = i;
                
                int cnt = (right - index) * (index - left);
                res += cnt * a[index];
            }
            
            stack.push(i);
        }
        
        return res;
    }
}
