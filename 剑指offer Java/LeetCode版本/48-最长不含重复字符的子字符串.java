/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

 

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
 

提示：

s.length <= 40000
 */

// 很常规的一道题
class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] used = new boolean[128];
        int res = 0;
        int start = 0, end = 0;
        while (end < s.length()) {
            if (used[s.charAt(end)] == true) {
                used[s.charAt(start)] = false;
                start++;
            } else {
                used[s.charAt(end)] = true;
                res = Math.max(res, end - start + 1);
                end++;
            }
        }

        return res;
    }
}