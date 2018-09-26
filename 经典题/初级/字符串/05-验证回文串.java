/*
验证回文字符串
给定一个字符串，确定它是否是回文，只考虑字母数字字符和忽略大小写。
例如：
"A man, a plan, a canal: Panama" 是回文字符串。
"race a car" 不是回文字符串。
注意:
你有考虑过这个字符串可能是空的吗？ 在面试中这是一个很好的问题。
针对此题目，我们将空字符串定义为有效的回文字符串。
 */

//使用前后指针
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        int front = 0;
        int end = s.length() - 1;
        while (front < end) {
            while (front < s.length() && !isValid(s.charAt(front))) {
                front++;
            }
            if (front == s.length())
                return true;
            while (end >= 0 && !isValid(s.charAt(end))) {
                end--;
            }
            if (Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end)))
                return false;
            else {
                front++;
                end--;
            }
        }
        return true;

    }

    public boolean isValid(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
}

// 简化判定方法，并使用单指针（实际上依然是双指针，是指表达方式改变了，更加高效）
class Solution {
    public boolean isPalindrome(String s) {
        char[] charArr = s.toCharArray();
        char[] filterArr = new char[charArr.length];
        int filterCount = 0;
        for (int i = 0; i < charArr.length; i++) {
            if ((charArr[i] >= '0' && charArr[i] <= '9') || (charArr[i] >= 'a' && charArr[i] <= 'z')) {
                filterArr[filterCount++] = charArr[i];
            } else if (charArr[i] >= 'A' && charArr[i] <= 'Z') {
                filterArr[filterCount++] = (char) (charArr[i] + 32);
            }
        }
        for (int i = 0; i < filterCount / 2; i++) {
            if (filterArr[i] != filterArr[filterCount - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}