/*
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

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

// dp[i][j]表示用前i + 1个数字表示出j的个数
// dp[i][j] = dp[i-1][j - nums[i-1]] + dp[i-1][j + nums[i-1]]
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0)
            return 0;

        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum < Math.abs(S))
            return 0;

        int n = nums.length, m = (sum << 1) + 1;
        int[][] dp = new int[n][m];
        dp[0][sum - nums[0]] += 1;
        dp[0][sum + nums[0]] += 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j - nums[i] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i]]; // 从这一行将上一行的值pull下来
                }
                if (j + nums[i] < m) {
                    dp[i][j] += dp[i - 1][j + nums[i]];
                }
            }
        }

        return dp[n - 1][S + sum];
    }
}

// 优化，使用一维数组，每次更新即可
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (final int num : nums)
            sum += num;
        if (sum < S)
            return 0;
        final int kOffset = sum;
        final int kMaxN = sum * 2 + 1;
        int[] ways = new int[kMaxN];
        ways[kOffset] = 1;
        for (final int num : nums) {
            int[] tmp = new int[kMaxN];
            for (int i = num; i < kMaxN - num; ++i) {
                tmp[i + num] += ways[i]; // 相当于将上一行的值push到下一行
                tmp[i - num] += ways[i];
            }
            ways = tmp;
        }
        return ways[S + kOffset];
    }
}

// DFS
class Solution {
    private int ans;

    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (final int num : nums)
            sum += num;
        if (sum < Math.abs(S))
            return 0;
        ans = 0;
        dfs(nums, 0, S);
        return ans;
    }

    private void dfs(int[] nums, int d, int S) {
        if (d == nums.length) {
            if (S == 0)
                ++ans;
            return;
        }
        dfs(nums, d + 1, S - nums[d]); // 表示使用加法后，距离S还剩下S - nums[d]
        dfs(nums, d + 1, S + nums[d]); // 表示使用减法后，距离S还剩下S + nums[d]
    }
}