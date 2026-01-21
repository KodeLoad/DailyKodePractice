package GeeksForGeeks.StockSpanProblem;

import java.util.ArrayList;
import java.util.Stack;

/*

    0       1       2       3       :: index
    100     80      90      120     :: prices
    1       1       2       4   
    
    previous elements < or = to cur price

    BF: O(N**2)
    
    ofc:
        what was the last number which are good
        or last bad number
        
        100 80 70 40 20 10 90 95
        any lookup or any ds directly 100
        
        for next element if previous was not voilated then
        the passing elements would be same for cur as well

        one by one 
        * -> * -> ...
        LIFO :: stack
*/
class Solution {
    public ArrayList<Integer> calculateSpan(int[] price) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        
        int itr = 0;
        while (itr < price.length) {
            // hey is it violating
            // if yes remove (pop)
            while (
                !stack.isEmpty() &&
                price[stack.peek()] <= price[itr]
            ) {
                // remove unccesary good items
                stack.pop();
            }
            
            // valid index
            // count
            if (stack.isEmpty()) {
                // there is no such item
                // all good things
                res.add(itr + 1);
            } else {
                // there is some one violating
                res.add(itr - stack.peek());
            }
            
            // advance itr and stack
            stack.push(itr);
            itr++;
        }
        
        
        return res;
    }
}
