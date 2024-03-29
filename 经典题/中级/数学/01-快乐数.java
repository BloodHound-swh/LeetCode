/*
 * 快乐数
编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

示例: 

输入: 19
输出: true
解释: 
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
 */
// 如果不是快乐数必然会重复出现一些数字（我也不知道怎么证明）
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while (n != 1) {
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
            n = sum;
        }
        return true;
    }
}

// 优化速度
class Solution {
    public boolean isHappy(int n) {
        if (n == 1)
            return true;
        int result = 0;
        while (true) {
            while (n > 0) {
                result += Math.pow(n % 10, 2);
                n /= 10;

            }

            if (result == 1) {
                return true;
            } else if (result == 4) { // 出现4的时候必然不是快乐数
                return false;
            } else {
                n = result;
                result = 0;
            }
        }
    }
}