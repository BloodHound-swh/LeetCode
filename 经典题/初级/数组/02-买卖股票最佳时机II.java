/*  
买卖股票的最佳时机 II
假设有一个数组，它的第 i 个元素是一个给定的股票在第 i 天的价格。
设计一个算法来找到最大的利润。你可以完成尽可能多的交易（多次买卖股票）。
然而，你不能同时参与多个交易（你必须在再次购买前出售股票）。
*/

//这个问题可以被看作找到数组中所有的上升序列问题。例如，对于给定的5，1，2，3，4
// 从1买进然后从4卖出，和从1买进，2卖出，2买进，3卖出效果是一样的.
// 所以我们遍历一遍数组，找到所有上升的的序列

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] - prices[i] > 0)
                profit = profit + prices[i + 1] - prices[i];
        }
        return profit;
    }
}