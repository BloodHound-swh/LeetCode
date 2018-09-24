/**
 * 反转字符串

编写一个函数，其作用是将输入的字符串反转过来。

示例 1:

输入: "hello"
输出: "olleh"
示例 2:

输入: "A man, a plan, a canal: Panama"
输出: "amanaP :lanac a ,nalp a ,nam A"
 */

// 未看答案版
class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() <= 1)
            return s;
        char[] str = s.toCharArray(); // 字符串转数组
        for (int i = 0; i < str.length / 2; i++) {
            char temp = str[i];
            str[i] = str[str.length - 1 - i];
            str[str.length - 1 - i] = temp;
        }
        return String.valueOf(str); // 数组转字符串
    }
}

// 答案一
class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() < 2)
            return s;
        int l = 0, r = s.length() - 1;
        char[] a = s.toCharArray();
        while (l < r) {
            char c = a[l];
            a[l] = a[r];
            a[r] = c;
            ++l;
            --r;
        }
        return new String(a); // 这也是一种数组转字符串的方法
    }
}