import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // num = nums[i]
            // finding = target - nums[i]
            if (map.containsKey(target - nums[i])) {
                // we got the previous number
                return new int[] {i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}