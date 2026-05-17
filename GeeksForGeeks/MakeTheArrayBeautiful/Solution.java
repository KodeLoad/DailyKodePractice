package MakeTheArrayBeautiful;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*

    4   2   -2  1
    ^ ------>-- 
            ^

seen::    
[
    4   1
]
    
    
    4   -2   2  1
    
    2   1
    
    remove the last thing you seen
    
    zero lie?
    
    4   0   -2
    
    zero is +ve
    
    LIFO :: stack 
*/
class Solution {
    List<Integer> makeBeautiful(int[] a) {
        Stack<Integer> stack = new Stack<>();
        
        for (int n : a) {
            if (stack.isEmpty()) {
                stack.push(n);
                continue;
            }
            
            int curSign = stack.peek() >= 0? 1 : -1;
            int incSign = n >= 0? 1 : -1;
            
            if (curSign == incSign) {
                stack.push(n);
            } else {
                stack.pop();
            }
        }
        
        return new ArrayList<>(stack);
    }
}



