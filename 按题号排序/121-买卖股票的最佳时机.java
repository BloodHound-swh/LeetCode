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



// 未看答案版
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int min = prices[0];
        int max = 0;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > min) {
                max = prices[i];
                int currProfit = max - min;
                profit = Math.max(currProfit, profit);
            } else {
                min = prices[i];
            }
        }
        return profit;
    }
}

// 未看答案版2
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            int profit = prices[i] - minPrice;
            if (profit > 0) {
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }
}

// 答案，与上面相比，简洁一些
// 只需要遍历一次数组，用一个变量记录遍历过数中的最小值，然后每次计算当前值和这个最小值之间的差值最为利润，然后每次选较大的利润来更新。
// 当遍历完成后当前利润即为所求
public class Solution {
    public int maxProfit(int[] prices) {
        int res = 0, buy = Integer.MAX_VALUE;
        for (int price : prices) {
            buy = Math.min(buy, price);
            res = Math.max(res, price - buy);
        }
        return res;
    }
}

// 第二次复习
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        
        int maxProfit = 0;
        int low = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > low)
                maxProfit = Math.max(maxProfit, prices[i] - low);
            else 
                low = prices[i];
        }
        
        return maxProfit;
    }
}