import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] values = valueTwoSum(Arrays.copyOfRange(nums, 0, nums.length), target);
        if (values == null) {
            return null;
        }

        int index1 , index2;
        index1 = index2 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (index1 == -1 && values[0] == nums[i]) {
                index1 = i;
            } else if (index2 == -1 && values[1] == nums[i]) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                break;
            }
        }

        return new int[] {index1, index2};
    }

    public int[] valueTwoSum(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            int index = Arrays.binarySearch(nums, i+1, nums.length, num2);
            if (index >= 0 && index != i) {
                // indexing is disturbed
                return new int[] {nums[i], nums[index]};
            }
        }
        return null;
    }
}