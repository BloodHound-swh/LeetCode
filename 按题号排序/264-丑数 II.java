/**
 * 丑数 II
编写一个程序，找出第 n 个丑数。

丑数就是只包含质因数 2, 3, 5 的正整数。

示例:

输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
说明:  

1 是丑数。
n 不超过1690。
 */

// 动态规划进行求解，每一个当前状态依赖于之前的三个状态，求取最小值，为了避免相等元素的重复，需要用多个if，而不是if...else if..else...结构，需要注意。
class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            if (min == dp[i2] * 2)
                i2++;
            if (min == dp[i3] * 3)
                i3++;
            if (min == dp[i5] * 5)
                i5++;
            dp[i] = min;
        }
        return dp[n - 1];
    }
}