/**
 * 无重复字符的最长子串

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */

// 想到了双指针，但是窗口怎么做没有想出来。

// 答案一
// 使用HashSet，字母第一次出现则添加到set中，重复出现则去除掉首位直到重复位的字母。size（）则为子串的大小
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        int start = 0, end = 0;
        while (end < s.length()) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            } else {
                set.add(s.charAt(end));
                res = Math.max(res, end - start + 1);
                end++;
            }
        }
        return res;
    }
}

// 思想同上
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length();) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(j++));
            } else {
                set.add(s.charAt(i++));
                res = Math.max(res, set.size());
            }
        }
        return res;
    }
}

// 答案二
// 使用一个boolean[128]记录所有字母的使用情况，出现过一次则变成true，若重复则改变左右指针，并置false
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int left = 0, right = 0;
        int n = s.length();
        boolean[] used = new boolean[128];
        int max = 0;
        while (right < n) {
            if (used[s.charAt(right)] == false) {
                used[s.charAt(right)] = true;
                right++;
            } else {
                max = Math.max(max, right - left);
                while (left < right && s.charAt(right) != s.charAt(left)) {
                    used[s.charAt(left)] = false;
                    left++;
                }
                left++;
                right++;
            }
        }

        max = Math.max(max, right - left);
        return max;
    }
}

// 思想同上
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 0;
        boolean[] used = new boolean[128];
        for (int i = 0, j = 0; i < s.length();) {
            if (used[s.charAt(i)] == false) {
                used[s.charAt(i)] = true;
                res = Math.max(res, i - j + 1);
                i++;
            } else {
                used[s.charAt(j++)] = false;
            }
        }

        return res;
    }
}