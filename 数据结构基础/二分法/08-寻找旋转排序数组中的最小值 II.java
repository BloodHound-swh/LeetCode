/**
 * 寻找旋转排序数组中的最小值 II
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

注意数组中可能存在重复的元素。

示例 1：

输入: [1,3,5]
输出: 1
示例 2：

输入: [2,2,2,0,1]
输出: 0
说明：

这道题是 寻找旋转排序数组中的最小值 的延伸题目。
允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 */

// 二分法，模板三
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int left = 0, right = nums.length - 1, res = nums[0];
        if (nums[left] < nums[right])
            return nums[left];
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {
                // res = Math.min(res, nums[left]);
                left = mid;
            } else if (nums[left] > nums[mid]) {
                // res = Math.min(res, nums[left]);
                right = mid;
            } else {
                left++; // 这是可能之后的数组正好全是升序，所以需要一次判断
                if (nums[left] < nums[right]){
                    return nums[left];
                }
            }
        }
        
        res = Math.min(nums[left], nums[right]);
        return res;
    }
}

