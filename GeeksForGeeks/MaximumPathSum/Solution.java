
class Node {
    int data;
    Node left, right;
    
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

/**
 * Solution for Maximum Path Sum in Binary Tree
 * 
 * Key Insights:
 * - Path can start and end at any nodes (need not include root)
 * - Path can go through parent or be contained within subtrees
 * - At each node, we consider two cases:
 *   1. Path passes through current node (connecting left and right subtrees)
 *   2. Path extends from current node to parent (either via left or right subtree)
 */
class Solution {
    private int globalMaxSum = Integer.MIN_VALUE;
    
    /**
     * Finds the maximum path sum in the binary tree
     * @param root - root of the binary tree
     * @return maximum path sum possible
     */
    int findMaxSum(Node root) {
        calculateMaxPathSum(root);
        return globalMaxSum;
    }
    
    /**
     * Recursively calculates maximum path sum for each subtree
     * 
     * This method serves two purposes:
     * 1. Updates the global maximum by considering paths that pass through current node
     * 2. Returns the maximum path sum that can be extended to the parent
     * 
     * @param currentNode - current node being processed
     * @return maximum path sum from current node that can be extended upward
     */
    private int calculateMaxPathSum(Node currentNode) {
        // Base case: null node contributes 0 to path sum
        if (currentNode == null) {
            return 0;
        }
        
        int currentNodeValue = currentNode.data;
        
        // Get maximum path sums from left and right subtrees
        // Use Math.max(0, ...) to ignore negative contributions
        int maxLeftPathSum = Math.max(0, calculateMaxPathSum(currentNode.left));
        int maxRightPathSum = Math.max(0, calculateMaxPathSum(currentNode.right));
        
        // Case 1: Path passes through current node (left -> current -> right)
        // This path cannot be extended further up, so we only update global maximum
        int pathThroughCurrentNode = maxLeftPathSum + currentNodeValue + maxRightPathSum;
        globalMaxSum = Math.max(globalMaxSum, pathThroughCurrentNode);
        
        // Case 2: Return the maximum path that can be extended to parent
        // Either go through left subtree or right subtree (not both)
        return Math.max(maxLeftPathSum, maxRightPathSum) + currentNodeValue;
    }
}
