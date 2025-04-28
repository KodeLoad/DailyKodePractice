class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals)
    {
        // Sort
        std::sort(intervals.begin(), intervals.end());
        
        vector<vector<int>> result;
        for(int index=0; index<intervals.size(); ++index)
        {
            int start=intervals[index][0];
            int end=intervals[index][1];

            //Check if this interval lies in already merged interval
            if(!result.empty() && end<=result.back()[1])
            {
                continue;
            }
        
            for(int subIndex=index+1; subIndex<intervals.size(); ++subIndex )
            {
                if(end>=intervals[subIndex][0])
                {
                    end=std::max(end,intervals[subIndex][1]);
                }
                else{
                    break;
                }
            }

            result.push_back({start,end});
        }

        return result;
    }
};
