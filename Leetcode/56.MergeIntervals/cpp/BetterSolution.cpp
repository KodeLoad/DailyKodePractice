#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    vector<vector<int> > merge(vector<vector<int> >& intervals)
    {
        // Sort
        std::sort(intervals.begin(), intervals.end());
        
        vector<vector<int> > result;
        for(int index=0; index<intervals.size(); ++index)
        {
            int start=intervals[index][0];
            int end=intervals[index][1];
            if(result.empty() || result.back()[1]<start)
            {
                result.push_back({start,end});
            }
            else
            {
                result.back()[1]=std::max(result.back()[1], intervals[index][1]);
            }
        }

        return result;
    }
};
