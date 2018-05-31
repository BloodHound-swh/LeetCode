/**
 * 买卖股票的最佳时机
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */

// 根据买卖股票的特性，我们必须先低价买，再高价卖，这个找最大收益的过程实际上是找到目前为止的最低价。
// 在遍历价格数组时，根据这个动态更新的最低价和当前的价格可以算出当前卖股票最大能赚多少钱。
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for(int i=0;i<prices.length;i++){
            if(prices[i]<min)
                min = prices[i];
            if(prices[i]-min>profit)
                profit = prices[i]-min;
        }
        return profit;
    }
}

//使用数组存储
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[] dp = new int[n + 1]; // min value up to first i items
        dp[0] = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1], prices[i - 1]);
            profit = Math.max(profit, prices[i - 1] - dp[i]);
        }
        return profit;
    }
}

//使用两个循环，复杂度高
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    max = Math.max(max, prices[j] - prices[i]);
                }
            }
        }
        return max;
    }
}