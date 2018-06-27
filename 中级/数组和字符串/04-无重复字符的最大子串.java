/**
 * 无重复字符的最长子串
给定一个字符串，找出不含有重复字符的最长子串的长度。

示例：

给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。

给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。

给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 */



//使用一个boolean[128]记录所有字母的使用情况，出现过一次则变成true，若重复则改变左右指针，并置false
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int left = 0, right = 0;
        int n = s.length();
        boolean[] used = new boolean[128];
        int max = 0;
        while(right < n){
            if (used[s.charAt(right)] == false) {
                used[s.charAt(right)] = true;
                right++;
            } else {
                max = Math.max(max, right - left);
                while(left < right && s.charAt(right) != s.charAt(left)){
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

//使用HashSet，字母第一次出现则添加到set中，重复出现则去除掉首位直到重复位的字母。size（）则为子串的大小
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        int start = 0, end = 0;
        while(end < s.length()) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            } else {
                set.add(s.charAt(end));
                res = Math.max(res, end - start + 1);
                end ++;
            }
        }
        return res;
    }
}

//方法二的另一种写法
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        for(int i = 0, j = 0; i < s.length();) {
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