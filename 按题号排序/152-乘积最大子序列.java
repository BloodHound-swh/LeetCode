/**
 * 乘积最大子序列

给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

 */


// 为看答案没有写出，关键在于动态规划的步骤

// 答案一
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

// 优化，先判断一下nums[i] 是否大于0
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = nums[0];
        int currMax = nums[0];
        int currMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                currMax = Math.max(nums[i], currMax * nums[i]);
                currMin = Math.min(nums[i], currMin * nums[i]);
            } else {
                int tmp = currMax;
                currMax = Math.max(nums[i], currMin * nums[i]);
                currMin = Math.min(nums[i], tmp * nums[i]);
            }
            res = Math.max(res, currMax);
        }
        return res;
    }
}