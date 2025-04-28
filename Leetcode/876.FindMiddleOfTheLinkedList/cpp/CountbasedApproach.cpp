/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

 struct ListNode {
    int val;
    ListNode *next;
    ListNode(){}
    ListNode(int x){}
    ListNode(int x, ListNode *next){}
};


class Solution {
public:
    ListNode* middleNode(ListNode* head)
    {
        ListNode* temp=head;

        int length=0;
        while(temp)
        {
            length++;
            temp=temp->next;
        }    

        int mid = length / 2;
        
        temp=head;
        for(int index=0; index<mid; ++index)
        {
            temp=temp->next;
        }

        return temp;
    }
};
