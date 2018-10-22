/**
 * 反转字符串中的单词 III
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

示例 1:

输入: "Let's take LeetCode contest"
输出: "s'teL ekat edoCteeL tsetnoc" 
注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */

// 由于没有多余的空格了，所以可以使用上一题的两个辅助函数，将字符串中的每个单词单独翻转即可
class Solution {
    public String reverseWords(String s) {
        if (s == null)
            return null;
        char[] a = s.toCharArray();
        int n = a.length;
        reverseWords(a, n);
        return String.copyValueOf(a);
    }

    public void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    public void reverseWords(char[] a, int n) {
        int i = 0, j = 0;
        while (i < n) {
            while (i < j || i < n && a[i] == ' ') {
                i++;
            }
            while (j < i || j < n && a[j] != ' ') {
                j++;
            }
            reverse(a, i, j - 1);
        }
    }
}

// 将字符串转换成数组，然后每个单词单独翻转
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] str = s.toCharArray();
        int start = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                reverse(str, start, i - 1);
                start = i + 1;
            } else if (i == str.length - 1) {
                reverse(str, start, i);
            }
        }

        return String.valueOf(str);
    }

    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start++] = s[end];
            s[end--] = temp;
        }
    }
}

// 同方法二
class Solution {
    public String reverseWords(String s) {
        char[] res = s.toCharArray();
        int start = 0, end = 0;
        while (start < res.length) {
            end = s.indexOf(' ', start);
            if (end == -1) {
                reverse(res, start, res.length - 1);
                break;
            }
            reverse(res, start, end - 1);
            start = end + 1;
        }
        return new String(res);
    }

    public void reverse(char[] w, int l, int r) {
        while (l < r) {
            char t = w[l];
            w[l] = w[r];
            w[r] = t;
            l++;
            r--;
        }
    }
}