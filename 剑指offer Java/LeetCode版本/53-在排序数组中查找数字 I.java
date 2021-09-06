/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
统计一个数字在排序数组中出现的次数。

 

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
 

提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109

 */


// 考虑到原数组是排序数组，先找到target，再向两边走
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0, right = nums.length;
        int p = -1;
        // 二分法模板
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                p = mid;
                break;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (p < 0) {
            return 0;
        } else {
            int i = p, j = p;
            while (i > 0 && nums[i - 1] == target) {
                i--;
            }
            while (j < (nums.length - 1) && nums[j + 1] == target) {
                j++;
            }
            return j - i + 1;
        }
    }
}

// 官方题解，但是我觉得不太适合面试用，容易出错
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0, right = nums.length;
        int first = findFrist(nums, target);
        if (first == -1) {
            return 0;
        }
        int last = findLast(nums, target);
        return last - first + 1;
    }

    public int findFrist(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                right = mid;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }

        return -1;
    }

    public int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // 注意这里的区别
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }

        return -1;
    }
}