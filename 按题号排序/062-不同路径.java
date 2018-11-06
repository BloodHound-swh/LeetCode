/**
 * 不同路径

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？



例如，上图是一个7 x 3 的网格。有多少可能的路径？

说明：m 和 n 的值均不超过 100。

示例 1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 2:

输入: m = 7, n = 3
输出: 28
 */

// 未看答案错误，排列组合如何计算的程序没有正确写出

// 答案一，排列组合
// 运用排列组合的思想，因为一共要向右走n - 1次，向下走m - 1次，一共m + n - 2次，所以就是从中挑选向下走的的排列组合，或者向右走的排列组合
// C(m + n - 2, m - 1) 或者 C(m + n -2, n - 1)
// C(m, n) = m! / n!(m - n)!
//         = (m - n + 1) * (m - n + 2) * ... / n!
class Solution {
    public int uniquePaths(int m, int n) {
        int step = m + n - 2;
        int down = n - 1;
        double res = 1;
        for (int i = 1; i <= down; i++) {
            res = res * (step - down + i) / i;
        }
        return (int) res;
    }
}

// 答案二
// 使用变量dp表示到每个点有多少种路径，初始化上边界和下边界都为1，运用转移方程求重点值
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // initial
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // function : dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}

// 思路其实相同，但是减小了空间复杂度
class Solution {
    public int uniquePaths(int m, int n) {
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[j] = res[j] + res[j - 1];
            }
        }
        return res[n - 1];
    }
}