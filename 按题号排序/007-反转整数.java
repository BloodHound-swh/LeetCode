/*
 * 反转整数

给定一个 32 位有符号整数，将整数中的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 */

// 未看答案版
// 在大多数情况下正确，但是没有考虑到溢出的情况，也就是出现2^31以外的数字
class Solution {
    public int reverse(int x) {
        int res = 0;
        int flag = 1;
        if (x < 0) {
            flag = -1;
            x = flag * x;
        }
        while (x > 0) {
            int temp = x % 10;
            res = res * 10 + temp;
            x = x / 10;
        }

        res = flag * res;
        return res;
    }
}

// 这是对上面的修改
class Solution {
    public int reverse(int x) {
        long res = 0; // 注意这里用long
        int flag = 1;
        if (x < 0) {
            flag = -1;
            x = flag * x;
        }

        // 注意：-1 * Integer.MIN_VALUE = Integer.MIN_VALUE
        while (x > 0) {
            int temp = x % 10;
            res = res * 10 + temp;
            if (res > Integer.MAX_VALUE) {
                return 0;
            }
            x = x / 10;
        }

        res = flag * res;
        return (int) res; // 最后重新转型为int
    }
}

// 答案一
// 注意越界后返回0.先用long来计算，然后，把越界的处理掉
class Solution {
    public int reverse(int x) {
        long r = 0;
        while (x != 0) {
            r = r * 10 + x % 10;
            x = x / 10;
            if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) r;
    }
}

// 答案二，不用long
class Solution {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) { // 注意这里不是>0了，因为负数直接可用下面的循环
            int temp = res * 10 + x % 10;
            if (temp / 10 != res)
                return 0;
            res = temp;
            x = x / 10;
        }
        return res;
    }
}