/**
 * 2的幂
给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:

输入: 1
输出: true
解释: 2^0 = 1
示例 2:

输入: 16
输出: true
解释: 2^4 = 16
示例 3:

输入: 218
输出: false
 */



// 2的幂有一个特性，就是它的二进制表达中只有开头是1，后面全是0。
// 比如4是100。所以我们只要数出有多少个1，就可以判断是不是2的幂。
class Solution {
    public boolean isPowerOfTwo(int n) {
        int cnt = 0;
        while (n > 0) {
            if ((n & 1) == 1)
                cnt++;
            n = n >> 1;
        }

        return cnt == 1;
    }
}

// 对于刚所说的特性，其实我们不一定要数出几个1，实际上对于1000这种形式的二进制数，我们只要将它减1得到111，再做位与，一定是0。
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;

        n = n & (n - 1);

        return n == 0;
    }
}