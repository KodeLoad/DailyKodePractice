#include <string>
class Solution {
public:
    bool isAnagram(string s, string t)
    {
        // Time Complexity: O(n)
        // Space Complexity: O(n)
        int freq[26];
        for(char ch : s)
        {
            int index=ch-'a';
            freq[index]++;
        }

        for(char ch : t)
        {
            int index=ch-'a';
            freq[index]--;
        }

        for(int count : freq)
        {
            if(count!=0)
            {
                return false;
            }
        }

        return true;
    }
};
