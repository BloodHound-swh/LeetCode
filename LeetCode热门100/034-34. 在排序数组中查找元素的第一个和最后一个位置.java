/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 

示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]
 

提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109
 */

// 普通二分法，但是不是严格的nlogn复杂度
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int p = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                p = mid;
                break;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (p == -1) {
            return new int[] { -1, -1 };
        } else {
            int a = p, b = p;
            while (a > 0 && nums[a - 1] == target)
                a--;
            while (b < nums.length - 1 && nums[b + 1] == target)
                b++;
            return new int[] { a, b };
        }
    }
}

// leftIdx是第一个比「target - 1」大的元素的索引，也就是target的第一个索引；
// rightIdx呢，binarySearch(nums, target)返回的是第一个比target大的元素，减1则是返回target的最后一个元素了。
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target - 1);
        int rightIdx = binarySearch(nums, target) - 1;
        if (leftIdx <= rightIdx && nums[leftIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        } 
        return new int[]{-1, -1};
    }

    // 第一个大于 target 的数的下标
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}