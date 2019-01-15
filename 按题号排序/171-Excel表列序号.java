/**
 * Excel表列序号

给定一个Excel表格中的列名称，返回其相应的列序号。

例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
示例 1:

输入: "A"
输出: 1
示例 2:

输入: "AB"
输出: 28
示例 3:

输入: "ZY"
输出: 701
 */

// 未看答案时，有些不熟悉将字符转数字，并且忘了A为1而不是0

// 就是把它当做26进制，注意A是1不是0，所以要加1
class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sum = sum * 26 + (c - 'A' + 1);
        }

        return sum;
    }
}

// 方法二
class Solution {
    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();

        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            int temp = new Integer(chars[i]) - 64;
            for (int j = 0; j < chars.length - i - 1; j++) {
                temp *= 26;
            }

            count += temp;
        }

        return count;
    }
}

// 同方法二
class Solution {
    public int titleToNumber(String s) {
        char[] c = s.toCharArray();
        int ans = 0;
        int j = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            ans += Math.pow(26, j++) * (c[i] - 'A' + 1);
        }
        return ans;
    }
}