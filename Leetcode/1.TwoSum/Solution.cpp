class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {

        unordered_map<int,int> mp;
        vector<int> result;
        for(int index=0; index<nums.size(); ++index)
        {
            int complement=target-nums[index];
            if(mp.find(complement) != mp.end())
            {
                result.push_back(mp[complement]);
                result.push_back(index);
            }

            mp[nums[index]] = index;
        }

        return result;
    }
};
