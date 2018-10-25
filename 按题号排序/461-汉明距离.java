/**
 * 汉明距离

两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：
0 ≤ x, y < 231.

示例:

输入: x = 1, y = 4

输出: 2

解释:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

上面的箭头指出了对应二进制位不同的位置。
 */


// 未看答案没有做出，当时的想法是每次将二进制最后一位与1，然后得到的结果应该如何操作没有方案

// 答案一，不是用什么异或，且，或之类的组合操作，就简单的用!=就可以了
class Solution {
    public int hammingDistance(int x, int y) {
        int distance = 0;
        while (x != 0 || y != 0) {
            if ((x & 1) != (y & 1)) {
                distance++;
            }
            x = x >>> 1;
            y = y >>> 1;
        }
        return distance;
    }
}

// 思路同方法一的两种写法
// 逐位比较
class Solution {
    public int hammingDistance(int x, int y) {
        int Distance = 0;
        for (int i = 0; i < 32; i++) {
            if (x % 2 != y % 2)
                Distance++;
            x >>= 1;
            y >>= 1;
        }
        return Distance;
    }
}

// 优化上面的方法
class Solution {
    public int hammingDistance(int x, int y) {
        int count = 0;
        for (int i = 31; i >= 0; i--)
            if ((x >>> i & 1) != (y >>> i & 1)) {
                count++;
            }
        return count;
    }
}

//使用z来标记不同地方，然后使用z的位1的个数来统计不同位的个数
class Solution {
    public int hammingDistance(int x, int y) {
        int Distance = 0;
        int z = x ^ y;
        while (z != 0) {
            Distance += z & 1;
            z = z >>> 1;
        }
        return Distance;
    }
}