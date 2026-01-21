package GeeksForGeeks.StockSpanProblem;

import java.util.ArrayList;
import java.util.Stack;

/*

    stack
        0(100) ;; 1(8) ;;
        0(100) ;; 2(90) ;;
        

*/

class Solution {
    public ArrayList<Integer> calculateSpan(int[] arr) {
        // code here
        ArrayList<Integer> ansList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        
        int n = arr.length;
        int i = 0;
        
        while(i < n){
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]){
                stack.pop();
            }
            
            if(stack.isEmpty()) {
                ansList.add(i+1);
            } else {
                ansList.add(i-stack.peek());
            }
            
            stack.push(i);
            i++;
        }
        
        return ansList;
    }
}