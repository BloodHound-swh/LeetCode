/**
 * 阶乘后的零
给定一个整数 n，返回 n! 结果尾数中零的数量。

示例 1:

输入: 3
输出: 0
解释: 3! = 6, 尾数中没有零。
示例 2:

输入: 5
输出: 1
解释: 5! = 120, 尾数中有 1 个零.
说明: 你算法的时间复杂度应为 O(log n) 。
 */


// 看n的阶乘中，一共有多少个5(把所有的数都拆成质数)
// 注意25 = 5 * 5，所以需要n = n / 5;
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            res = res + n / 5;
            n = n / 5;
        }
        return res;
    }
}

// 递归，还是方法一更加直观
class Solution {
    public int trailingZeroes(int n) {
        if (n >= 5) {
            return n / 5 + trailingZeroes(n / 5);
        } else {
            return 0;
        }  
    }
}