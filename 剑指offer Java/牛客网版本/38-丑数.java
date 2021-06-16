/**
 * 题目描述
把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */

// 同LeetCode第264题
// 动态规划进行求解，每一个当前状态依赖于之前的三个状态，求取最小值，为了避免相等相等元素的重复，需要用多个if，而不是if...else if..else...结构，需要注意。
public class Solution {
    public int GetUglyNumber_Solution(int n) {
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