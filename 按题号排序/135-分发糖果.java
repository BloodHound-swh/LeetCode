/**
 * 分发糖果
n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

你需要按照以下要求，给这些孩子分发糖果：

每个孩子至少分配到 1 个糖果。
相邻两个孩子评分更高的孩子会获得更多的糖果。
请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。

 

示例 1：

输入：ratings = [1,0,2]
输出：5
解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
示例 2：

输入：ratings = [1,2,2]
输出：4
解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
     第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 

提示：

n == ratings.length
1 <= n <= 2 * 10^4
0 <= ratings[i] <= 2 * 10^4
 */


// 先给每个小朋友一个糖果，然后从左到右遍历一遍，再从右到左遍历一遍
class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        if (ratings.length == 1) {
            return 1;
        }

        int[] candys = new int[ratings.length];
        for (int i = 0; i < candys.length; i++) {
            candys[i] = 1;
        }

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candys[i] = candys[i - 1] + 1;
            }
        }

        for (int j = ratings.length - 2; j >= 0; j--) {
            if (ratings[j] > ratings[j + 1]) {
                // 若已近大于了，则不需要变动
                if (candys[j] > candys[j + 1]) {
                    continue;
                }
                candys[j] = candys[j + 1] + 1;
            }
        }

        int res = 0;
        for (int n : candys) {
            res += n;
        }

        return res;
    }
}