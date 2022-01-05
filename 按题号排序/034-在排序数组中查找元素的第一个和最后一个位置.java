/**
 * 在排序数组中查找元素的第一个和最后一个位置

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
*/

// 在考虑到用二分法找到目标后的处理不清楚

// 答案一
// 考虑到原数组是排序数组，先找到target，再向两边走
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int i = 0, j = nums.length;
        int p = -1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == target) {
                p = mid;
                break;
            }
            if (nums[mid] > target) {
                j = mid;
            } else {
                i = mid + 1;
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

// 答案二
// 修改一下二分法模板，使其一个不断往左找，一个不断往右找。注意当nums[mid] == target时的处理
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