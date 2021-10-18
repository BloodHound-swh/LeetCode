/**
 * 剑指 Offer 65. 不用加减乘除做加法
写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

 

示例:

输入: a = 1, b = 1
输出: 2
 

提示：

a, b 均可能是负数或 0
结果不会溢出 32 位整数

 */


// 个人对这类题不太感冒，就直接看大佬的答案了，面试真的会出这类题吗？
// https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solution/mian-shi-ti-65-bu-yong-jia-jian-cheng-chu-zuo-ji-7/
class Solution {
    public int add(int a, int b) {
        // 当进位为 0 时跳出
        while(b != 0) { 
            // c = 进位
            int c = (a & b) << 1;  
            // a = 非进位和
            a ^= b; 
            // b = 进位
            b = c; 
        }
        return a;
    }
}