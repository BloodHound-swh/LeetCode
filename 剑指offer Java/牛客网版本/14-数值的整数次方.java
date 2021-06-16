/**
 * 题目描述
给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */


// https://www.youtube.com/watch?v=yEQq3t3T_J0
public class Solution {
    public double Power(double base, int exponent) {
        if (exponent == 0 || base == 1)
            return 1;
        if (exponent == 1)
            return base;
        if (exponent < 0)
            return 1.0 / Power(base, -exponent);

        double res = 1;
        while (exponent > 1) {
            if (exponent % 2 == 1)
                res = res * base;
            base = base * base;
            exponent = exponent / 2;
        }
        res = res * base;

        return res;
    }
}