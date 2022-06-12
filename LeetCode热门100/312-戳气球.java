/**
 * 312. 戳气球
有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。

求所能获得硬币的最大数量。

 

示例 1：
输入：nums = [3,1,5,8]
输出：167
解释：
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
示例 2：

输入：nums = [1,5]
输出：10
 

提示：

n == nums.length
1 <= n <= 300
0 <= nums[i] <= 100
 */


// 大神很强
// https://www.youtube.com/watch?v=Ci39lcoLbyw&t=198s
// 先用coins[]来记录每个气球的值（并把0先戳破），且在左右边界各补上1
// 再用maxCoin[i][j]来表示戳破(i,j)之间所有气球得到的最大值
// 假设最后戳破(left, right)中的i位置的气球，则对于最后一步得到的分值为coins[i] * coins[left] * coins[right]
// 然后在加上(left, i) 和 (i, right) 得到的maxCoin即可
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


// 官方题解，思路差不多
class Solution {
    public int[][] rec;
    public int[] val;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        val = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = val[n + 1] = 1;
        rec = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(rec[i], -1);
        }
        return solve(0, n + 1);
    }

    public int solve(int left, int right) {
        if (left >= right - 1) {
            return 0;
        }
        if (rec[left][right] != -1) {
            return rec[left][right];
        }
        for (int i = left + 1; i < right; i++) {
            int sum = val[left] * val[i] * val[right];
            sum += solve(left, i) + solve(i, right);
            rec[left][right] = Math.max(rec[left][right], sum);
        }
        return rec[left][right];
    }
}