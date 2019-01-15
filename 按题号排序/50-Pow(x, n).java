/**
 * Pow(x, n)

实现 pow(x, n) ，即计算 x 的 n 次幂函数。

示例 1:

输入: 2.00000, 10
输出: 1024.00000
示例 2:

输入: 2.10000, 3
输出: 9.26100
示例 3:

输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
说明:

-100.0 < x < 100.0
n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 */

// 未看答案只想到了循环，而且没有考虑MIN直接取反是得不到MAX的

// 答案一
// https://www.youtube.com/watch?v=yEQq3t3T_J0
// 使用“二分法”避免使用简单的循环来减少复杂度
class Solution {
    public double myPow(double x, int n) {
        if (n == 0 || x == 1)
            return 1;
        if (n == 1)
            return x;
        if (n < 0)
            return 1 / (x * myPow(x, -(n + 1))); // 防止MIN_VALUE取负时溢出，注意前面乘了一个x，所以后面括号里加了1
        double res = 1;
        while (n > 1) {
            if (n % 2 == 1)
                res = res * x;
            x = x * x;
            n = n / 2;
        }
        res = res * x;
        return res;
    }
}

// 答案二
// 好像不用管溢出也没事，应该是测试样例的问题，或者说此时的值已经趋于零了，是测不出来区别的。。。
class Solution {
    public double myPow(double x, int n) {
        boolean reverse = false;
        if(n < 0) {
            n = -n;
            reverse = true;
        }
        double tmp = x; 
        double res = 1;
        for(int i = 0; i < 32; i++) {
            if((n & 1) == 1) res *= tmp;
            n = n >> 1;
            tmp = tmp * tmp;
        }
        return reverse ? 1.0/res : res;
    }
}