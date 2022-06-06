/**
 * 完全平方数

给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.
 */



// 未看答案没有做出，完全没有思路。。。。

// 如果一个数x可以表示为一个任意数a加上一个平方数bｘb，也就是x=a+bｘb
// 那么能组成这个数x最少的平方数个数，就是能组成a最少的平方数个数加上1（因为b*b已经是平方数了）。
// https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51584790
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // 将所有非平方数的结果置最大，保证之后比较的时候不被选中
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i * i <= n; i++) {
            dp[i * i] = 1;
        }
        // 从小到大找任意数a
        for (int a = 0; a <= n; a++) {
            // 从小到大找平方数bｘb
            for (int b = 0; a + b * b <= n; b++) {
                // 因为a+b*b可能本身就是平方数，所以我们要取两个中较小的
                dp[a + b * b] = Math.min(dp[a] + 1, dp[a + b * b]);
            }
        }

        return dp[n];
    }
}

// 动态规划。这是一道非常非常典型的动态规划问题。
// 1. 首先如何定义问题？这个问题就是，f(i)=对于任意一个正整数i，求i由多少个整数平方和组成。
// 2. 如何得到最终问题f(n)？方法就是从1开始计算，f(1)，f(2)，直到f(n)
// 3. 每个f(i)如何计算？每个f(i)只和前面的f值有关！！！
public class Solution {
    public int numSquares(int n) {
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++)
            nums[i] = i;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                nums[i] = Math.min(nums[i], nums[i - j * j] + 1);
            }
        }
        return nums[n];
    }
}

// 思想同上
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 最坏的情况就是均由1组成
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}