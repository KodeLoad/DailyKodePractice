package GeeksForGeeks.Sort0s,1sAnd2s;

/*
    0 1 2
    
    do it in single pass?
    
    it is important to understand the streaming data?
    
    0 1 1 1 1 2 2 22222
        -> x = 0 (put it in right place)
    place => insertion point
    right place => 3 right insertion point
                    2 right insertion point
                    
    
    sorted ::
    [  .... zero's ins point ...... two's ins point ... ]
        
    x => 0 -> insert in 0's insertion point
         1 -> next to 0
         2 -> insert in 2's insertion point
        
    0001101010101022
          iiii
    000 | 111 |null (a.length - 1)
    0000 | 111 | 2  (a.length - 2)
*/
class Solution {
    void swap(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }
    
    public void sort012(int[] a) {
        int zi, ti, i;
        zi = 0;
        ti = a.length - 1;
        
        i = 0;
        while (i <= ti) {
            int x = a[i];
            switch(x) {
                case 0:
                    swap(a, zi, i);
                    zi++;
                    i++;
                    break;
                case 1:
                    i++;
                    break;
                case 2:
                    swap(a, ti, i);
                    ti--;
                    break;
            }
        }
    }
}
