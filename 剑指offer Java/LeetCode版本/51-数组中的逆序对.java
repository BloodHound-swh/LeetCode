/**
 * 剑指 Offer 51. 数组中的逆序对
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

 

示例 1:

输入: [7,5,6,4]
输出: 5
 

限制：

0 <= 数组长度 <= 50000
 */


// 强烈推荐官方题解里的视频，讲的太好了
// https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/
class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // 使用copy的原因是为了不改变原数组
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }

        int[] tmp = new int[nums.length];
        return helper(copy, 0, nums.length - 1, tmp);
    }

    public int helper(int[] nums, int left, int right, int[] tmp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftCount = helper(nums, left, mid, tmp);
        int rightCount = helper(nums, mid + 1, right, tmp);

        // 一个小优化，当出现这种情况时可以不用mergeAndCount
        if (nums[mid] <= nums[mid + 1]) {
            return leftCount + rightCount;
        }

        int crossCount = mergeAndCount(nums, left, mid, right, tmp);
        return crossCount + leftCount + rightCount;
    }

    public int mergeAndCount(int[] nums, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }

        int count = 0;
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == (mid + 1)) {
                nums[k] = tmp[j];
                j++;
            } else if (j == (right + 1)) {
                nums[k] = tmp[i];
                i++;
            } else if (tmp[i] <= tmp[j]) {
                nums[k] = tmp[i];
                i++;
            } else {
                nums[k] = tmp[j];
                j++;
                count += (mid - i + 1);
            }
        }

        return count;
    }
}