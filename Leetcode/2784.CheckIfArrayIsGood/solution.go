package leetcode_2784

/*
naive appraoch

	create base
	n
	base[1] = [1, 1] and base[3] = [1, 2, 3, 3]
	check base array equivalent to input array

better way but still not up to mark

	input arr : a
	freq map (space)
	    create (loop)
	    check each number from (1 -> n) (loop)
	        1 -> n-1 :: only 1
	               n :: should 2

avoid 2 loops and not use space

	1   2  3  4  :: index
	-1 -3  3  2  :: array

	I've removed the space
*/
func isGood(a []int) bool {
	max := len(a) - 1
	rep := 0
	const MaxRep = 2

	for _, val := range a {
		if val < 0 {
			val *= -1 // copy
		}

		index := val - 1

		// check bounds
		if index < 0 || index >= max {
			return false
		}

		// exactly once for rest
		if a[index] < 0 && val != max {
			// visited already
			return false
		}

		// exactly twice for max
		if val == max {
			rep++
			if rep > MaxRep {
				return false
			}
		}

		// mark visited
		a[index] *= -1
	}

	return true
}
