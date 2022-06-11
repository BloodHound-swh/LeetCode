/**
 * 309. 最佳买卖股票时机含冷冻期
给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 

示例 1:

输入: prices = [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
示例 2:

输入: prices = [1]
输出: 0
 

提示：

1 <= prices.length <= 5000
0 <= prices[i] <= 1000
 */


// 可以将状态简化成两种，股票持有和股票不持有
// 使用两个数组来记录如果第i天不持有股票的状态或者持有的状态的最大利润
// hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i])
// unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i])
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[] hold = new int[prices.length];
        int[] unhold = new int[prices.length];

        hold[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (i == 1) {
                hold[i] = Math.max(hold[i - 1], -prices[1]);
            } else {
                hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
            }
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
        }

        return unhold[prices.length - 1];
    }
}

// for循环中的判断次数减少判断
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int n = prices.length;
        int[] hold = new int[n];
        int[] unhold = new int[n];

        hold[0] = -prices[0];
        hold[1] = Math.max(hold[0], -prices[1]);
        unhold[1] = Math.max(unhold[0], hold[0] + prices[1]);

        for (int i = 2; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
        }

        return unhold[n - 1];
    }
}