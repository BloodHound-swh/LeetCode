/*
 * 在排序数组中查找元素的第一个和最后一个位置 
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目
 *  值在数组中的开始位置和结束位置。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 
 * 示例 1:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 8 输出: [3,4] 示例 2:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 6 输出: [-1,-1]
 */

// 使用二分法模版，不容易出错，注意当nums[mid] == target时的处理
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = { -1, -1 };
        if (nums == null || nums.length == 0)
            return res;
        int st_point = -1, end_point = -1;
        int start = 0, end = nums.length - 1;
        // find the start point
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target)
                end = mid;
            else
                start = mid;
        }
        if (nums[start] == target)
            st_point = start;
        else if (nums[end] == target)
            st_point = end;
        if (st_point == -1)
            return res;

        // find the end point
        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target)
                end = mid;
            else
                start = mid; // nums[mid] <= target
        }
        if (nums[end] == target)
            end_point = end;
        else if (nums[start] == target)
            end_point = start;
        res[0] = st_point;
        res[1] = end_point;
        return res;
    }
}

// 先找到target，再向两边走
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int i = 0, j = nums.length;
        int mid = (i + j) / 2;

        int p = -1;
        while (i < j) {
            if (nums[mid] == target) {
                p = mid;
                break;
            }
            if (nums[mid] > target) {
                if (j == mid)
                    break;
                j = mid;
                mid = (i + j) / 2;
            } else {
                if (i == mid)
                    break;
                i = mid;
                mid = (i + j) / 2;
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