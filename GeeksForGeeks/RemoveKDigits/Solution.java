package GeeksForGeeks.RemoveKDigits;

/*
    
    what number shall I call greater
    
    6 9     -> smaller
    9 6     -> larger
    
    6 6 9   -> smallest
    6 9 6   -> mid
    9 6 6   -> largest
    
    0           1
    first -> next from first -> 
    
    smallest -> smaller -> small -> med -> ...
    
    idea :: smaller number should come first
        if a non smaller number comes first then remove!
        
    
    4   3   2   5   0   4   3
    *   
        *   
            2   *   0   4   3
            
            2 0 4 3
    
*/
class Solution {
    public String removeKdig(String s, int k) {
        Stack<Character> stack = new Stack<>();
        
        // clean flush
        if (k >= s.length()) {
            return "0";
        }
        
        // step 1: build the stack
        for (int i = 0; i < s.length(); i++) {
            var cur = s.charAt(i);
            
            while (
                k > 0 &&
                !stack.isEmpty() && 
                stack.peek() > cur
            ) {
                // remove the stack item
                // remove the prev item
                stack.pop();
                k--;
            }
            
            stack.push(cur);
        }
        
        // k > 0 we have scope to remove more
        // 1 2 3 4 5 && k = 2
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        // build the result
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        sb.reverse();
        
        // remove leading zeros
        int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }
        var res = sb.substring(i);
        
        
        // return
        return res.length() == 0? "0" : res;
    }
}
