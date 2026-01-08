package GeeksForGeeks.CountSubarrayWithKOdds;

import java.security.NoSuchAlgorithmException;

/*
    BF:
        for i 
            for j
                <i, j> :: odd 
        O(n**2)

    
    optimal 1 
        k = 2
        0  1  2  3
        2, 5, 6, 9
        0  1  1  2
        
        for index 0 (ie, 2) -> even number 0
        but, i dont no number chosen?
                  0 -> no choice
        
    map : (odd_count_in_array) -> freq
            0 -> 2
            1 -> 2
            2 -> 1
        
        2, 5, 6, ..
        2 -> 1                      -> result
        1 -> 1(from 5) and          -> result
        next 1 ?
        
        k = 2
        ..X...... 100 101 2003
          29      30  31
          

    optimal 2
        exactly k odd elements?
        
        exactK(k)
            atMost(K) - atMost(k-1)

            
*/

public interface Solution {
    int countSubarrays(int[] a, int k) throws NoSuchAlgorithmException;
}

enum ApproachType {
    NO_EXTRA_SPACE,
    STORAGE_WITH_MAP,
    STORAGE_WITH_ARRAY;
}

class SolutionImpl implements Solution {
    final ApproachType PREFFERED_APPROACH = ApproachType.STORAGE_WITH_ARRAY;

    @Override
    public int countSubarrays(int[] a, int k) throws NoSuchAlgorithmException {
        return switch (PREFFERED_APPROACH) {
            case ApproachType.NO_EXTRA_SPACE ->  new NoExtraSpaceSolution().countSubarrays(a, k);
            case ApproachType.STORAGE_WITH_ARRAY ->  new StorageWithArraySolution().countSubarrays(a, k);
            case ApproachType.STORAGE_WITH_MAP -> new StorageWithMapSolution().countSubarrays(a, k);
            default -> throw new NoSuchAlgorithmException();
        };
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        var obj = new SolutionImpl();

        int[] a = new int[] {
            2, 5, 6, 9
        };

        int k = 2;

        System.out.println(
            obj.countSubarrays(a, k)
        );
    }

}