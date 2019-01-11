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

// 没什么好说的，直接看的答案

// 我们可知2的数量远大于5的数量，那么此题即便为找出5的个数。仍需注意的一点就是，像25,125，这样的不只含有一个5的数字需要考虑进去
// 注意25 = 5 * 5，所以需要n = n / 5;
// (100)! -> (100 / 5) + (100 / 25) + (100 / 125) = 20 + 4 + 0 = 24
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