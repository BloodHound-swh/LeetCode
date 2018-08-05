/**
 * 跳跃游戏
给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

示例 1:

输入: [2,3,1,1,4]
输出: true
解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
示例 2:

输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
// 使用max_reach来记录当前所能到达的最远处，当max_reach大于nums.length-1时，返回true（贪心算法）
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length < 2)
            return true;
        int max_reach = 0;
        int i = 0;
        for (i = 0; i < nums.length && i <= max_reach; i++) {
            max_reach = Math.max(i + nums[i], max_reach);
            if (max_reach >= nums.length - 1)
                return true;
        }

        return false;
    }
}

// 优化方法一
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max)
                return false;
            max = Math.max(i + nums[i], max);
        }

        return true;
    }
}

// 使用数组dp[] 来表示走到i位置时的剩余“跳力”，当跳力小于零时，表示其实根本无法到达i位置
class Solution {
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1]) - 1;
            if (dp[i] < 0)
                return false;
        }
        return dp[nums.length - 1] >= 0;
    }
}