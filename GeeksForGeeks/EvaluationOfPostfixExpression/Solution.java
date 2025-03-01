package GeeksForGeeks.EvaluationOfPostfixExpression;

import java.util.Stack;

/*
    "2", "3", "1", "*", "+", "9", "-"
    "5", "X", "X", "X", "X", "9", "-"
    "-4", "X", "X", "X", "X", "9", "-"
    
    Last elements first :: Stack
    
    2 
    2 3
    2 3 1
    2 3 1 * => pop out 2 elements => 3 * 1 -> 3(push)
    2 3 +   => pop out 2 elements => 2 + 3 -> 5(push)
    5 9 -   => pop out 2 elements => 5 - 9 -> -4 (push)
    -4 end of array
    
    
    return -4
    
*/

public class Solution {
    public int evaluate(String[] a) {
        // the calculate only when we see an operator
        // else push the numbers in stack

        final Stack<Integer> stack = new Stack<>();
        final String operators = "+-*/";
        for (String s : a) {
            /*
            3 -5 
            */
            char ch = s.charAt(0);
            if (operators.indexOf(s) >= 0) {
                // it is an operator
                // so calculate this
                int second = stack.pop();
                int first = stack.pop();
                int result = calculate(first, ch, second);
                stack.push(result);
                continue;
            }
            
            stack.push(Integer.parseInt(s));
        }
        
        return stack.pop();
    }
    
    private int calculate(int a, char op, int b) {
        switch(op) {
            case '+': return a+b;
            case '-': return a-b;
            case '/': return a/b;
            case '*': return a*b;
        }

        // throw an exception
        return -1;
    }
}

