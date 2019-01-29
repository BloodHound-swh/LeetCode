/**
 * 两整数之和 
 * 不使用运算符 + 和-，计算两整数a 、b之和。
 * 
 * 示例： 若 a = 1 ，b = 2，返回 3。
 */



 
// 很明显计算机本身是不动+ - 的，它的底层是通过 异或 和 与 两个位运算来实现的，异或 是不带进位的结果， 与 是进位。
// 方法一，递归
class Solution {
    public int getSum(int a, int b) {
        int result = a ^ b;
        int forward = (a & b) << 1;
        if (forward != 0) // 看进位是否为0
            return getSum(result, forward);
        return result;
    }
}

// 与方法一同样的思路，但是不用递归了
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int add = (a & b) << 1;
            a = a ^ b;
            b = add;
        }
        return a;
    }
}