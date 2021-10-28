/**
 * 3. 无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
示例 4:

输入: s = ""
输出: 0
 

提示：

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成
 */


// 使用HashSet，字母第一次出现则添加到set中，重复出现则去除掉首位直到重复位的字母。size（）则为子串的大小
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0, right = 0;
        int res = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                res = Math.max(res, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }

        return res;
    }
}

// 思想同上, 使用一个boolean[128]记录所有字母的使用情况
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