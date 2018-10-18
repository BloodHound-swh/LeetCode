// 未看答案版，有错误，递归的公式不对
class Solution {
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int[] a = new int[n + 1];
        a[1] = 1;
        a[2] = 2;
        for (int i = 1; i < n + 1; i++) {
            a[n] = a[n - 2] + 2;
        }
        return a[n];
    }
}

// 答案一，对上面的方法做出修改就可以成为正确答案
// 每一层的楼梯走法等于上一层的走法再走一步加上两层的走法再走两步，所以用递归的方法，只与前两个状态有关。
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

// 使用pre和current可以减少空间复杂度。
class Solution {
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int pre = 1;
        int current = 2;
        for (int i = 3; i <= n; i++) {
            current = pre + current;
            pre = current - pre;
        }
        return current;
    }
}