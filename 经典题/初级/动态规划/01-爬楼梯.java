/*
 爬楼梯
假设你正在爬楼梯。需要 n 步你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：

输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 步 + 1 步
2.  2 步
示例 2：

输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 步 + 1 步 + 1 步
2.  1 步 + 2 步
3.  2 步 + 1 步 
 */

// 每一层的楼梯走法等于上一层的走法再走一步加上两层的走法再走两步，所以用递归的方法，至于前两个状态有关。
// 使用pre和current可以减少空间复杂度。
class Solution {
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int pre = 1;
        int current = 2;
        for (int i = 3; i <= n; i++) {
            current = pre + current;
            pre = current - pre;
        }
        return current;
    }
}

// 使用数组，但是空间复杂度高
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

// 超时
class Solution {
    public int climbStairs1(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }
}