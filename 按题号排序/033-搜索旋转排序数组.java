/**
 * 搜索旋转排序数组

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
示例 2:

输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
 */

// 未看答案时，想到使用二分法，但是判定条件没有写出

// 答案一,模板三变形
// 旋转数组必然分成两段考虑，所以分两种情况，在左端时考虑右指针向mid移动的情况，在右段时考虑左指针向mid移动的情况
// 注意等号不能少
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] < nums[mid]) {
                if (nums[mid] >= target && nums[left] <= target)
                    right = mid;
                else
                    left = mid;
            } else {
                if (nums[mid] <= target && nums[right] >= target)
                    left = mid;
                else
                    right = mid;
            }
        }

        if (nums[left] == target)
            return left;
        else if (nums[right] == target)
            return right;

        return -1;
    }
}

// 如果不要等号
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] < nums[mid]) {
                if (nums[mid] == target)
                    return mid;
                else if (nums[mid] > target && nums[left] <= target)
                    right = mid;
                else
                    left = mid;
            } else {
                if (nums[mid] == target)
                    return mid;
                else if (nums[mid] < target && nums[right] >= target)
                    left = mid;
                else 
                    right = mid;
            }
        }
        
        if (nums[left] == target)
            return left;
        else if (nums[right] == target)
            return right;
        
        return -1;
    }
}

// // 使用的是模板一的变形，不如模板三
class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length, left = 0, right = len - 1;
        return binarySearch(nums, left, right, target);
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[left] <= nums[mid]) { // 注意等号，如输入为[3,1]要求找到1。
                if (nums[left] <= target && nums[mid] >= target)
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (nums[mid] <= target && nums[right] >= target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}

// 第二次复习，使用模板一，且不用helper函数
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } if (nums[left] <= nums[mid]) {
                if (target < nums[mid] && nums[left] <= target)
                    right = mid - 1;
                else 
                    left = mid + 1;
            } else if (nums[mid] <= nums[right]) {
                if (target > nums[mid] && nums[right] >= target) 
                    left = mid + 1;
                else 
                    right = mid - 1;
            }
        }
        
        return -1;
    }
}