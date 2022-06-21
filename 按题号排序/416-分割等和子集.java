/**
 * 分割等和子集
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

 

示例 1：

输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：

输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。
 

提示：

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */


// 0-1背包问题,传统的「0-10−1 背包问题」要求选取的物品的重量之和不能超过背包的总容量，这道题则要求选取的数字的和恰好等于整个数组的元素和的一半。
// 类似于传统的「0-10−1 背包问题」，可以使用动态规划求解
// dp[i][j] 用来标识数组坐标范围[0, i]是否可以凑出和为j的子集
// 返回值就是dp[n - 1][sum / 2]
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        // 如果为奇数则直接返回false
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // 若是最大值已经超过合的一半, 也可以直接返回false
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        dp[0][nums[0]] = true;
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i] == j) {
                    dp[i][j] = true;
                } else if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }
}

// 节省空间复杂度
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }
}