/**
 * 戳气球
有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。

求所能获得硬币的最大数量。

说明:

你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
示例:

输入: [3,1,5,8]
输出: 167 
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */


// 没有想出动态规划的转移方程，而这是最重要的一步。。。 


// 答案
// https://www.youtube.com/watch?v=Ci39lcoLbyw&t=198s
// 先用coins[]来记录每个气球的值（并把0先戳破），且在左右边界各补上1
// 再用maxCoin[i][j]来表示戳破(i,j)之间所有气球得到的最大值
// 假设最后戳破(left, right)中的i位置的气球，则对于最后一步得到的分值为coins[i] * coins[left] * coins[right]
// 然后在加上(left, i) 和 (i, right) 得到的maxCoin即可
class Solution {
    public int maxCoins(int[] nums) {
        int[] coins = new int[nums.length + 2];
        int n = 1;
        // 先戳破所有值为0的气球
        for (int c : nums) {
            if (c != 0) {
                coins[n++] = c;
            }
        }

        coins[0] = coins[n++] = 1;
        // maxCoin[i][j]表示戳破(i,j)之间所有气球得到的最大值
        int[][] maxCoin = new int[n][n]; // 注意此时n的值已经在上一步加了1

        for (int len = 2; len < n; len++) { // 相当于是从三个气球开始遍历，逐渐计算到到n个气球
            for (int left = 0; left + len < n; left++) {
                int right = left + len;
                for (int i = left + 1; i <= right - 1; i++) {
                    maxCoin[left][right] = Math.max(maxCoin[left][right],
                            coins[i] * coins[left] * coins[right] + maxCoin[left][i] + maxCoin[i][right]);
                }
            }
        }

        return maxCoin[0][n - 1];
    }
}

// 同上
class Solution {
    public int maxCoins(int[] nums) {
        int[] coins = new int[nums.length + 2];
        int n = 1;
        for (int coin : nums) {
            // 相当于提前戳破了值为0的气球，减少一定的运算量
            if (coin != 0) {
                coins[n++] = coin;
            }
        }
        // 根据题意，边界直接设置为1
        coins[0] = 1;
        coins[n] = 1;
        n = n + 1;
        // maxCoin[i][j]表示戳破(i,j)之间所有气球得到的最大值,注意此时n的值已经在上一步加了1
        int[][] maxCoin = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int left = 0; left + len < n; left++) {
                int right = left + len;
                for (int i = left + 1; i <= right - 1; i++) {
                    maxCoin[left][right] = Math.max(maxCoin[left][right], coins[left] * coins[i] * coins[right] + maxCoin[left][i] + maxCoin[i][right]);
                }
            }
        }
        return maxCoin[0][n - 1];
    }
}