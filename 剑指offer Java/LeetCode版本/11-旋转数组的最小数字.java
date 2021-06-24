/**
 * 剑指 Offer 11. 旋转数组的最小数字
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

示例 1：

输入：[3,4,5,1,2]
输出：1
示例 2：

输入：[2,2,2,0,1]
输出：0
 */

class Solution {
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }

        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int pivot = left + (right - left) / 2;
            if (numbers[pivot] > numbers[right]) { // 说明numbers[pivot]是最小值左侧的元素，且numbers[pivot]不是最小元素
                left = pivot + 1;
            } else if (numbers[pivot] < numbers[right]) { // 说明numbers[pivot]是最小值右侧的元素
                right = pivot;
            } else if (numbers[pivot] == numbers[right]) { // 由于数字可能重复，所以此时不能确定最小值在numbers[pivot]的左侧还是右侧，只能让边界right减1
                right--;
            } 
        }

        return numbers[right];
    }
}

// 官方题解
class Solution {
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }
}