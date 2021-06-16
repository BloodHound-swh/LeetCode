/**
 * 题目描述
大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
n<=39
 */


// 递归的方法可能会遇到Stack Overflow，
// 所以我们可以考虑用动态规划的方法来实现。
// 采用自底向上方法来保存了先前计算的值，为后面的调用服务。
public class Solution {
    public int Fibonacci(int n) {
        if (n == 0 || n == 1)
            return n;

        int n1 = 0;
        int n2 = 1;
        for (int i = 2; i <= n; i++) {
            n2 = n1 + n2;
            n1 = n2 - n1;
        }

        return n2;
    }
}