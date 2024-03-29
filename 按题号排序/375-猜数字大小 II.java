/**
 * 猜数字大小 II

题目描述
评论 (30)
题解
提交记录
我们正在玩一个猜数游戏，游戏规则如下：

我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。

每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。

然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。

示例:

n = 10, 我选择了8.

第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。

游戏结束。8 就是我选的数字。

你最终要支付 5 + 7 + 9 = 21 块钱。
给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 */


// http://www.cnblogs.com/grandyang/p/5677550.html
// 如果只有一个数字，那么我们不用猜，cost为0。
// 如果有两个数字，比如1和2，我们猜1，即使不对，我们cost也比猜2要低。
// 如果有三个数字1，2，3，那么我们就先猜2，根据对方的反馈，就可以确定正确的数字，所以我们的cost最低为2。
// 如果有四个数字1，2，3，4，那么情况就有点复杂了，那么我们的策略是用k来遍历所有的数字，然后再根据k分成的左右两个区间，取其中的较大cost加上k。
// 当k为1时，左区间为空，所以cost为0，而右区间2，3，4，根据之前的分析应该取3，所以整个cost就是1+3=4。
// 当k为2时，左区间为1，cost为0，右区间为3，4，cost为3，整个cost就是2+3=5。
// 当k为3时，左区间为1，2，cost为1，右区间为4，cost为0，整个cost就是3+1=4。
// 当k为4时，左区间1，2，3，cost为2，右区间为空，cost为0，整个cost就是4+2=6。
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i > 0; i--) {
                int localMin = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int max = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    localMin = Math.min(localMin, max);
                }
                dp[i][j] = i + 1 == j ? i : localMin;// 当i == j - 1时，dp[i][j]即为i j中的较小者i
            }
        }
        return dp[1][n];
    }
}


// 虽然道理上将好像也需要比较k==i和k==j的时候，但实际上发现并不需要。。。
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i > 0; i--) {
                int localMin = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int max;
                    if (k == i) {
                        max = k + dp[k + 1][j];
                    } else if (k == j) {
                        max = k + dp[i][k - 1];
                    } else {
                        max = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    }
                    localMin = Math.min(localMin, max);
                }
                dp[i][j] = i + 1 == j ? i : localMin;// 当i == j - 1时，dp[i][j]即为i j中的较小者i
            }
        }
        return dp[1][n];
    }
}