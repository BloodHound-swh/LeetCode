/**
 * 颠倒二进制位
颠倒给定的 32 位无符号整数的二进制位。

示例:

输入: 43261596
输出: 964176192
解释: 43261596 的二进制表示形式为 00000010100101000001111010011100 ，
     返回 964176192，其二进制表示形式为 00111001011110000010100101000000 。
进阶:
如果多次调用这个函数，你将如何优化你的算法？
 * 
 */


//让res左移,n右移,并每次把最后一位放到res目前的最后一位上。
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if(n == 0)
            return 0;
        int res = 0;
        for(int i = 0; i < 32; i++){
            res <<= 1;
            if((n & 1) == 1)
                res++;
            n >>= 1;
        }
        return res;
    }
}

//代码优化
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int reversed = 0;
        for (int i = 0; i < 32; i++) {
            reversed = (reversed << 1) | (n & 1);
            n = (n >> 1);
        }
        return reversed;
    }
}
