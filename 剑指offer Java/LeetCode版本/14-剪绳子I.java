/**
 * 剑指 Offer 14- I. 剪绳子
给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

示例 1：

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
提示：

2 <= n <= 58
 */

// 首先定义d(n)为把长度为n的绳子剪成若干段后各段长度乘积的最大值。
// 在剪第一刀时，我们有n-1种选择，也就是说第一段绳子的可能长度分别为1,2,3.....，n-1。因此d(n)=max(d(i)*d(n-i))，其中0<i<n。
// 这是一个自上而下的递归公式。由于递归会有大量的不必要的重复计算。
// 一个更好的办法是按照从下而上的顺序计算，也就是说我们先得到d(2),d(3)，再得到d(4),d(5)，直到得到d(n)。
// 特殊情况有，长度为2时，最大乘积为1 × 1 = 1，长度为3时，最大乘积为1 × 2 = 2
class Solution {
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int[] dp = new int[n + 1];
        // 这里的0，1，2，3是剪下来的最小单元，当绳子的长度大于3时，总是由这几个元素组成的。
        // 也可以理解为长度为1，2，3时就不要剪了，再剪反而会变小，所以大于4的情况下d[2] = 2, d[3] = 3
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }

        return dp[n];
    }
}