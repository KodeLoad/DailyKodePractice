package Leetcode

/*
elemnts are unique

minVal, maxVal

	| ----->

figuring out?

	array?
	num is present in array?
	O(n * n)?
	  ^   1
	O(n * 1)
	Quick lookup :: hashmap
*/
func findMissingElements(nums []int) []int {
	minVal, maxVal := nums[0], nums[0]
	seenMap := make(map[int]struct{}, len(nums))

	for _, n := range nums {
		minVal = min(minVal, n)
		maxVal = max(maxVal, n)
		seenMap[n] = struct{}{}
	}

	res := make([]int, 0)
	for i := minVal + 1; i < maxVal; i++ {
		_, seen := seenMap[i]
		if !seen {
			res = append(res, i)
		}
	}

	return res
}
