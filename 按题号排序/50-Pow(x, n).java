// 未看答案只想到了循环，而且没有考虑MIN直接取反是得不到MAX的


// 答案一
// https://www.youtube.com/watch?v=yEQq3t3T_J0
// 使用“二分法”避免使用简单的循环来减少复杂度
class Solution {
    public double myPow(double x, int n) {
        if (n == 0 || x == 1)
            return 1;
        if (n == 1)
            return x;
        if (n < 0)
            return 1 / (x * myPow(x, -(n + 1))); // 防止MIN_VALUE取负时溢出
        double res = 1;
        while (n > 1) {
            if (n % 2 == 1)
                res = res * x;
            x = x * x;
            n = n / 2;
        }
        res = res * x;
        return res;
    }
}