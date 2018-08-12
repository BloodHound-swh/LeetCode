/**
 * x 的平方根
实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:

输入: 4
输出: 2
示例 2:

输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 
     由于返回类型是整数，小数部分将被舍去。
 */


// 使用二分法，看mid的平方与x的大小关系
class Solution {
    public int mySqrt(int x) {
        if (x <= 0)
            return 0;
        int maxNum = (int) Math.sqrt(Integer.MAX_VALUE);
        int start = 1;
        int end = maxNum;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (mid * mid == x)
                return mid;
            if (mid * mid > x)
                end = mid;
            else
                start = mid;
        }
        if (end * end <= x) // 不要漏掉等号
            return end;
        else
            return start;
    }
}

// 因为各平方数之间的差值是个等差数列，可以找到通项公式
// https://www.youtube.com/watch?v=JtZBs9Qy_6M
class Solution {
    public int mySqrt(int x) {
        if (x <= 0)
            return 0;
        int curr = 0;
        int res = 0;
        int add = 1;
        while (curr <= x) {
            if (Integer.MAX_VALUE - curr < add)
                return res;
            curr = curr + add;
            res++;
            add = add + 2;
        }
        return res - 1;
    }
}

// 牛顿迭代法，直接使用数学公式
public class Solution {
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        double y = Math.max(1, x / 2);
        while (true) {
            double ny = (((double) y * y + x) / 2 / y);
            if (Math.abs(y - ny) <= 0.01)
                return (int) ny;
            y = ny;
        }
    }
}