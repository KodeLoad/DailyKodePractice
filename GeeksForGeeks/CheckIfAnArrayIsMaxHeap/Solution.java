package GeeksForGeeks.CheckIfAnArrayIsMaxHeap;

/*
    max heap :
        cur should always be < parent

    cur and the parent
    left & rights

    root
    left  -> root * 2 + 1
    right -> root * 2 + 

    cur
    parent -> (cur - 1) / 2
*/
class Solution {
    public boolean isMaxHeap(int[] a) {
        return checkInididual(a, 0);
    }

    boolean checkInididual(int[] a, int cur) {
        // validation check
        if (cur >= a.length) {
            return true;
        }

        // cur check
        int parent = (cur - 1) / 2;
        if (a[cur] > a[parent]) {
            return false;
        }

        int left  = cur * 2 + 1;
        int right = cur * 2 + 2;

        return checkInididual(a, left)
            && checkInididual(a, right);
    }
}
