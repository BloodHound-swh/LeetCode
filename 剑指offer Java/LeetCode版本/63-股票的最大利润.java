/**
 * 剑指 Offer 63. 股票的最大利润
假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

 

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 

限制：

0 <= 数组长度 <= 10^5
 */

// 此题思路比较简单，就是用一个变量来记录买入时的价格，当今天的价格比昨天小时，那买入值就应该是今天而不是昨天（如果现实里也能这样就无敌了）
// 这算不算是LeetCode版不切实际题（一遍放水一遍加水这种无语题）
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int maxBenefit = 0;
        int buyPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buyPrice) {
                buyPrice = prices[i];
                continue;
            } else {
                maxBenefit = Math.max(maxBenefit, prices[i] - buyPrice);
            }
        }

        return maxBenefit;
    }
}