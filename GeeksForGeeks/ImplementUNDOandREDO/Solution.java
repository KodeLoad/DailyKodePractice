package GeeksForGeeks.ImplementUNDOandREDO;

import java.util.Stack;

/*

    undo:
        A -> B -> C
        undo 
        A -> B :: remove C
        undo 
        A :: remove B
        add Z
        
        AZ
        undo
        :: remove Z
        
        what is added last 
        undo would remove the last
        
        last in first out
        Stack<>
        
    redo:
        Stack<>

    append:
        StrinbBuilder


    A B C
        undo
        remove C
        remove that C from sb
        
        redo
        Append C back again
        
    
    A B Z
    undo:: A B Z
    redo:: C

*/
class Solution {
    StringBuilder sb = new StringBuilder();
    Stack<Character> undo = new Stack<>();
    Stack<Character> redo = new Stack<>();
    
    public void append(char ch) {
        // append x into document
        sb.append(ch);
        undo.push(ch);
    }

    public void undo() {
        // undo last change
        char ch = undo.pop();
        sb.setLength(sb.length() - 1);
        redo.push(ch);
    }

    public void redo() {
        // redo changes
        char ch = redo.pop();
        append(ch);
    }

    public String read() {
        // read the document
        return sb.toString();
    }
}
