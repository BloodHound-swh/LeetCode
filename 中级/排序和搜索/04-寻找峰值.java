
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