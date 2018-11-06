/**
 * 零钱兑换
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

示例 1:

输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1
示例 2:

输入: coins = [2], amount = 3
输出: -1
说明:
你可以认为每种硬币的数量是无限的。
 */


// 未看答案没有做出来，感觉这不是中等题。。。

// https://www.youtube.com/watch?v=htdBJul3xoc
// 时间复杂度较高，但是易于理解，使用f[i][j]表示使用coins[i, i + 1, ... n - 1]来拼出j的最小个数
// f[i][j] = min {f[i + 1][j]
//                f[i + 1][j - 1 * coins[i]] + 1
//                ...
//                f[i + 1][j - k * coins[i]] + k}

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] f = new int[coins.length + 1][amount + 1];
        Arrays.fill(f[coins.length], Integer.MAX_VALUE);
        f[coins.length][0] = 0;

        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                f[i][j] = f[i + 1][j];
                int maxK = j / coins[i];
                for (int k = 1; k <= maxK; k++) {
                    int prev = f[i + 1][j - k * coins[i]];
                    if (prev < Integer.MAX_VALUE) {
                        f[i][j] = Integer.min(f[i][j], prev + k);
                    }
                }
            }
        }
        if (f[0][amount] == Integer.MAX_VALUE)
            return -1;
        else
            return f[0][amount];
    }
}

// f[i][j] = min(f[i + 1][j],
//               f[i + 1][j - 1 * coins[i]] + 1,
//               f[i + 1][j - 2 * coins[i]] + 2,
//               ...)
//         = min(f[i + 1][j],
//               min (f[i + 1][j - 1 * coins[i]] + 1,
//                    f[i + 1][j - 2 * coins[i]] + 2,
//                    ...))
//         = min(f[i + 1][j],
//               min (f[i + 1][j - 1 * coins[i]],
//                    f[i + 1][j - 2 * coins[i]] + 1,
//                    ...) + 1)
//         = min(f[i + 1][j],
//               f[i][j - 1 * coins[i]] + 1)
// 所以只需考虑f[i + 1][j]和f[i][j - 1 * coins[i]] + 1
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] f = new int[coins.length + 1][amount + 1];
        Arrays.fill(f[coins.length], Integer.MAX_VALUE);
        f[coins.length][0] = 0;
        
        for(int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j <=amount; j++) {
                f[i][j] = f [i + 1][j];
                if (j >= coins[i]) {
                    int prev = f[i][j - coins[i]];
                    if (prev < Integer.MAX_VALUE) {
                        f[i][j] = Integer.min(f[i][j], prev + 1);
                    }
                }
            }
        }
        if (f[0][amount] == Integer.MAX_VALUE)
            return -1;
        else 
            return f[0][amount];
    }
}

// 节省空间复杂度
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] g = new int[amount + 1];
        Arrays.fill(g, Integer.MAX_VALUE);
        g[0] = 0;
        for (int i = coins.length - 1; i>=0; i--) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    int prev = g[j - coins[i]];
                    if (prev < Integer.MAX_VALUE) {
                        g[j] = Integer.min(g[j], prev + 1);
                    }
                }
            }
        }
        return g[amount] == Integer.MAX_VALUE? -1 : g[amount];
    }
}

// 优化if判断的次数
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1;i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
        }
        for (int a : coins) {
            for (int i = a; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - a] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE -1? -1 : dp[amount];
    }
}