/**
 * 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

示例: 

输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
进阶:

如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */



// O(n)的解法
// 我们需要定义两个指针left和right，分别记录子数组的左右的边界位置，然后我们让right向右移，直到子数组和大于等于给定值或者right达到数组末尾
// 此时我们更新最短距离，并且将left像右移一位，然后再sum中减去移去的值，然后重复上面的步骤，直到right到达末尾，且left到达临界位置
// 即要么到达边界，要么再往右移动，和就会小于给定值
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int left = 0, right = 0;
        int res = nums.length + 1;
        int sum = 0;

        while (right < nums.length) {
            while (sum < s && right < nums.length) {
                sum += nums[right++];
            }

            while (sum >= s) {
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
        }

        return res == nums.length + 1 ? 0 : res;
    }
}