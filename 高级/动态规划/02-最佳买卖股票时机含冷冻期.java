/**
 * Best Time to Buy and Sell Stock with Cooldown
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */


// 使用两个数组来记录如果第i天保持卖出的状态或者保持买入的状态的最大利润
// hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i])
// unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i])
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int n = prices.length;
        int[] hold = new int[n];
        int[] unhold = new int[n];

        hold[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            if (i == 1)
                hold[i] = Math.max(hold[i - 1], -prices[1]);
            else
                hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
        }

        return unhold[n - 1];
    }
}

// 为了for循环中的判断次数减少判断
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

// 使用，三个数组来维护
// hold[i] = Math.max(hold[i - 1], rest[i - 1] - prices[i]);
// sold[i] = hold[i - 1] + prices[i];
// rest[i] = Math.max(rest[i - 1], sold[i - 1]);
// max = Math.max(rest[i], sold[i]);
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int[] hold = new int[prices.length], sold = new int[prices.length], rest = new int[prices.length];
        hold[0] = -prices[0];
        sold[0] = 0;
        rest[0] = 0;
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(hold[i - 1], rest[i - 1] - prices[i]);
            sold[i] = hold[i - 1] + prices[i];
            rest[i] = Math.max(rest[i - 1], sold[i - 1]);
            max = Math.max(rest[i], sold[i]);
        }
        return max;
    }
}

// 优化空间复杂度
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int sold = 0;
        int rest = 0;
        int hold = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            int pre_sold = sold;
            sold = hold + prices[i];
            hold = Math.max(hold, rest - prices[i]);
            rest = Math.max(rest, pre_sold);
        }

        return Math.max(rest, sold); // 因为rest比sold慢一天，所以最后返回时需要比较一下
    }
}