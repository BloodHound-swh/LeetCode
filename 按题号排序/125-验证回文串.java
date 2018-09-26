/**
 * 验证回文串

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false
 */


// 未看答案版，只能解决全是小写字母的回文串判定
// 而且在while内部的判定语句处理的不好
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) >= 'a' && s.charAt(left) <= 'z' && s.charAt(right) >= 'a' && s.charAt(right) <= 'z') {
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            } else {
                while (s.charAt(left) < 'a' || s.charAt(left) > 'z') {
                    left++;
                }
                while (s.charAt(right) < 'a' || s.charAt(right) > 'z') {
                    right--;
                }
            }
        }
        return true;
    }
}

// 答案一，运用java自带的Character.isLetter()和Character.isDigit()方法
// Character.toLowerCase()如果有小写则转为小写，否则返回本身
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

// 答案二，不依赖java自身的判定方法，自己写。
// 简化判定方法，并使用单指针（实际上依然是双指针，是指表达方式改变了）
class Solution {
    public boolean isPalindrome(String s) {
        char[] charArr = s.toCharArray();
        char[] filterArr = new char[charArr.length];
        int filterCount = 0;
        for (int i = 0; i < charArr.length; i++) {
            if ((charArr[i] >= '0' && charArr[i] <= '9') || (charArr[i] >= 'a' && charArr[i] <= 'z')) {
                filterArr[filterCount++] = charArr[i];
            } else if (charArr[i] >= 'A' && charArr[i] <= 'Z') {
                filterArr[filterCount++] = (char) (charArr[i] + 32); // filterArr[filterCount++] = Character.toLowerCase(s.charAt(i));
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