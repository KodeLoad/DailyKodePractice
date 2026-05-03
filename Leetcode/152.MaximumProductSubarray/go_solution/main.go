package maini

import "slices"

/*
2    3    -2    4
^----------^

	-2
	shall we reset or optimistic?
	         |
	         +-> at what point?
	             at what point my multiplication is garuenteed to not go up?
	             zero (0) -> -ve / +ve still be zero
	             reset whenever we see this.

-2    3    2    4
*/
func maxProduct(nums []int) int {
	// l to r
	process := func(nums []int) int {
		res := nums[0]
		mul := 1

		for _, num := range nums {
			mul *= num
			res = max(res, mul)
			if mul == 0 {
				mul = 1
			}
		}

		return res
	}

	ltr := process(nums)
	slices.Reverse(nums)
	rtl := process(nums)

	return max(ltr, rtl)
}
