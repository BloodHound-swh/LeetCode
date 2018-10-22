// 未看答案版
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n = n >>> 1;
        }
        return res;
    }
}

// 以下答案个人感觉都没有上面的答案好

// 答案一
// 常规思路，逐位与1相与，计数。
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;
            n >>= 1;
        }
        return res;
    }
}

// 答案二
// 使用n & n-1 ,重复操作，有多少个1，这个操作就可以执行多少次。
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}

// 答案二的另一种写法
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        if (n == 0) {
            return 0;
        }

        int count = 1;
        while ((n & (n - 1)) != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }
}