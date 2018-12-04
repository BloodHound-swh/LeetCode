/*
 * 寻找峰值 峰值元素是指其值大于左右相邻值的元素。
 * 
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * 
 * 示例 1:
 * 
 * 输入: nums = [1,2,3,1] 输出: 2 解释: 3 是峰值元素，你的函数应该返回其索引 2。 示例 2:
 * 
 * 输入: nums = [1,2,1,3,5,6,4] 输出: 1 或 5  解  你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。 说明:
 * 
 * 你的解法应该是 O(logN) 时间复杂度的。
 */

// 使用二分法，更新边界，当nums[mid] > nums[mid + 1]时，说明峰值在左边，否则在右边
class Solution {
    public int findPeakElement(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (nums[m] > nums[m + 1])
                j = m;
            else
                i = m + 1;
        }
        return i;
    }
}

// 全局扫描，但是复杂度为O(N)，不符合题意
public class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return 0;
        if (nums[0] > nums[1])
            return 0;
        if (nums[nums.length - 2] < nums[nums.length - 1])
            return nums.length - 1;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1])
                return i;
        }
        return -1;
    }
}

// 修改一下全局扫描，还是二分法
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int l = 0, r = nums.length - 1;
        while (true) {
            int mid = (l + r) / 2;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
                l = mid;
            } else {
                r = mid;
            }
        }
    }
}