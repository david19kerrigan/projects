//Run the .go file with "go run mission.go"
//each line is inputted one at a time
//totals are calculated after each test case is inputted

package main

import (
	"fmt"
	"strconv"
)

func getTestCase(i int) {
	if i > 0 {
		fmt.Print("first line(X): ")

		var text string
		fmt.Scanln(&text)
		n, _ := strconv.Atoi(text)

		fmt.Print("test cases(Yn): ")
		allNums := make([]int, n)
		readNumbers(allNums, 0, n)

		fmt.Println("total:", sumNums(allNums, 0, n, 0))

		fmt.Scan(&allNums)
		fmt.Scanln(&text)

		getTestCase(i - 1)
	}
}

func sumNums(nums []int, pos int, length int, sum int) int {
	if pos < length {
		if nums[pos] > 0 {
			return sumNums(nums, pos+1, length, sum+nums[pos]*nums[pos])
		}
		if nums[pos] <= 0 {
			return sumNums(nums, pos+1, length, sum)
		}

	}
	return sum
}

func readNumbers(allNums []int, i, j int) {
	if j == 0 {
		return
	}
	fmt.Scan(&allNums[i])
	readNumbers(allNums, i+1, j-1)
}

func main() {
	fmt.Print("first line(N): ")

	var text string
	fmt.Scanln(&text)
	j, _ := strconv.Atoi(text)

	getTestCase(j)
}
