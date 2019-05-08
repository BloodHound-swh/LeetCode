/**
 * 题目描述
给你一根长度为n的绳子，请把绳子剪成m段 (m和n都是整数，n>1并且m>1)每段绳子的长度记为k[0],k[1],...,k[m].
请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
例如，当绳子的长度为8时，我们把它剪成长度分别为2,3,3的三段，此时得到的最大乘积是18.
 */


// 首先定义函数f(n)为把长度为n的绳子剪成若干段后各段长度乘积的最大值。
// 在剪第一刀时，我们有n-1种选择，也就是说第一段绳子的可能长度分别为1,2,3.....，n-1。因此f(n)=max(f(i)*f(n-i))，其中0<i<n。
// 这是一个自上而下的递归公式。由于递归会有大量的不必要的重复计算。
// 一个更好的办法是按照从下而上的顺序计算，也就是说我们先得到f(2),f(3)，再得到f(4),f(5)，直到得到f(n)。
// 当绳子的长度为2的时候，只能剪成长度为1的两段，所以f(2) = 1，当n = 3时，容易得出f(3) = 2;
public class Solution {
    public int maxAfterCutting(int length) {
        if (length < 2)
            return 0;
        if (length == 2)
            return 1;
        if (length == 3)
            return 2;

        int[] f = new int[length + 1];
        // 这里的0，1，2，3是减下来的最小单元，当绳子的长度大于3时，总是由这几个元素组成的。
        f[0] = 0;
        f[1] = 1;
        f[2] = 2;
        f[3] = 3;
        for (int i = 4; i <= length; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int num = f[j] * f[i - j];
                if (max < num)
                    max = num;
                f[i] = max;
            }
        }

        int res = f[length];
        return res;
    }
}