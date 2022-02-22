/**
 * 70. 爬楼梯
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

 

示例 1：

输入：n = 2
输出：2
解释：有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶
示例 2：

输入：n = 3
输出：3
解释：有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶
 

提示：

1 <= n <= 45
 */


// 其实就是菲波那切数列
class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int p = 1, q = 2;
        for (int i = 3; i <= n; i++) {
            int tmp = q;
            q = p + q;
            p = tmp;
        }

        return q;
    }
}

// 进一步减小使用的内存
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

// 容易理解版本，每一层的楼梯走法等于上一层的走法再走一步加上两层的走法再走两步，所以用递归的方法，只与前两个状态有关。
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