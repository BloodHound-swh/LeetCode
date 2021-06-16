/**
 * 题目描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */


// leetcode第70题，爬楼梯原题
public class Solution {
    public int JumpFloor(int target) {
        if (target <= 1)
            return 1;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[target];
    }
}

// 其实就是菲波那切数列
class Solution {
    public int climbStairs(int n) {
        if (n <= 1)
            return 1;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }

        return second;
    }
}