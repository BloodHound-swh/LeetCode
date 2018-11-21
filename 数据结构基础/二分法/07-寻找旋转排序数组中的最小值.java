/**
 * 寻找旋转排序数组中的最小值
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

示例 1:

输入: [3,4,5,1,2]
输出: 1
示例 2:

输入: [4,5,6,7,0,1,2]
输出: 0
 */

// 二分法，模板三
class Solution {
    public int findMin(int[] nums) {
        if (nums[0] <= nums[nums.length - 1])
            return nums[0];
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[left]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return Math.min(nums[left], nums[right]);
    }
}


// 同上
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        if (nums[left] > nums[right]) {
            while (left + 1< right) {
                int mid = left + (right - left) / 2;
                if (nums[left] < nums[mid]) 
                    left = mid;
                else 
                    right = mid;
            }
            return Math.min(nums[left], nums[right]);
        }
        return nums[0];
    }
}