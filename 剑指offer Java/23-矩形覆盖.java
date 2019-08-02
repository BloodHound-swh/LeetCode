/**
 * 题目描述
我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
 	 
// https://www.cnblogs.com/wxisme/p/5236365.html	 	 	 	 
// 观察上面的矩形，如果使用2x1的矩形来覆盖2x8的矩形的话，设有f(n)中覆盖放法。
// 首先，第一个2x1的矩形有两种放法，第一种，竖着放，剩下的部分有f(7)种，第二种横着放，剩下的部分有f(6)种。
// 则一共有f(8) = f(7) + f(6)。很明显这又是著名的Fibonacci数列。
public class Solution {
    public int RectCover(int target) {
        if (target <= 2)
            return target;

        int pre = 1;
        int curr = 2;

        for (int i = 3; i <= target; i++) {
            curr = pre + curr;
            pre = curr - pre;
        }

        return curr;
    }
}