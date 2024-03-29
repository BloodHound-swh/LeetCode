/**
 * 目标和
给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

示例 1:

输入: nums: [1, 1, 1, 1, 1], S: 3
输出: 5
解释: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

一共有5种方法让最终目标和为3。
注意:

数组的长度不会超过20，并且数组中的值全为正数。
初始的数组的和不会超过1000。
保证返回的最终结果为32位整数。
 */


// 那么对于这种求多种情况的问题，最容易想到的方法使用递归来做。
// 我们从第一个数字，调用递归函数，在递归函数中，分别对目标值进行加上当前数字调用递归，和减去当前数字调用递归，这样会涵盖所有情况
// 并且当所有数字遍历完成后，我们看若目标值为0了，则结果res自增1
class Solution {
    private int res;

    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums)
            sum += num;

        if (sum < Math.abs(S))
            return 0;

        res = 0;
        helper(nums, S, 0);

        return res;
    }

    private void helper(int[] nums, int S, int idx) {
        if (idx == nums.length) {
            if (S == 0)
                res++;
            return;
        }

        helper(nums, S - nums[idx], idx + 1);
        helper(nums, S + nums[idx], idx + 1);
    }
}

// https://leetcode.cn/problems/target-sum/solution/mu-biao-he-by-leetcode-solution-o0cp/
// 背包问题
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                if (j < num) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }
}