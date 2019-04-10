/**
 * 反转字符串中的单词 III
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

示例 1:

输入: "Let's take LeetCode contest"
输出: "s'teL ekat edoCteeL tsetnoc" 
注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */


// 未看答案本，用到了split函数，应该也行吧。当然最好是不用这个函数的
class Solution {
    public String reverseWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String tmp = reverseString(word);
            sb.append(tmp + " ");
        }
        
        return sb.toString().trim();
    }
    
    public String reverseString(String str) {
        char[] s = str.toCharArray();
        int l = 0;
        int r = s.length - 1;
        
        while (l < r) {
            char c = s[l];
            s[l] = s[r];
            s[r] = c;
            l++;
            r--;
        }
        
        return new String(s);
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