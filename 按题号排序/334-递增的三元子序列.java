/**
 * 递增的三元子序列

给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。

数学表达式如下:

如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。

示例 1:

输入: [1,2,3,4,5]
输出: true
示例 2:

输入: [5,4,3,2,1]
输出: false
 */

// 未看答案版，只做出了有连续三个递增的情况
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length <= 2)
            return false;
        int left = 0;
        int right = 1;
        int len = 0;

        while (right < nums.length) {
            if (nums[right] > nums[right - 1]) {
                right++;
                len = Math.max(len, right - left);
            } else {
                left = right;
                right++;
            }
        }

        if (len >= 3)
            return true;
        else
            return false;
    }
}

// 答案
// 使用两个值分三个区间来维护，当三个区间均存在符合的数字时，即可返回true
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int start = Integer.MAX_VALUE;
        int medio = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < start) {
                start = nums[i];
            } else if (nums[i] > start && nums[i] < medio) {
                medio = nums[i];
            } else if (nums[i] > medio) {
                return true;
            }
        }
        return false;
    }
}