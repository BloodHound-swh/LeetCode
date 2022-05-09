/**
 * 152. 乘积最大子数组
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个 32-位 整数。

子数组 是数组的连续子序列。

 

示例 1:

输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 

提示:

1 <= nums.length <= 2 * 10^4
-10 <= nums[i] <= 10
nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 */

// 因为乘法的特性，当前的最小值很有可能再乘以下一个负数又变成了最大值
// 所以需要用currMax，currMin两个值来记录，并且
// currMax = Math.max(nums[i], preMax * nums[i], preMin * nums[i])
// currMin = Math.min(nums[i], preMax * nums[i], preMIn * nums[i])
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int max = nums[0];
        int currMax = nums[0];
        int currMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int nextMax = currMax * nums[i];
            int nextMin = currMin * nums[i];
            currMax = Math.max(nums[i], Math.max(nextMax, nextMin));
            currMin = Math.min(nums[i], Math.min(nextMax, nextMin));
            max = Math.max(max, currMax);
        }

        return max;
    }
}