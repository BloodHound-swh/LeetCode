/**
 * 76. 最小覆盖子串
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

 

注意：

对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
 

示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：

输入：s = "a", t = "a"
输出："a"
示例 3:

输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
 

提示：

1 <= s.length, t.length <= 105
s 和 t 由英文字母组成
 

进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */


// https://leetcode-cn.com/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
// 滑动窗口，官方视频题解讲的非常好
class Solution {
    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if (sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();

        int[] winFreq = new int[128];
        int[] tFreq = new int[128];

        for (char c : tCharArray) {
            tFreq[c]++;
        }

        // 滑动窗口内部包含多少t中的字符，对应字符频数超过不重复计算
        int match = 0;
        int minLen = sLen + 1;
        int begin = 0;

        // [left, right)
        int left = 0;
        int right = 0;
        while (right < sLen) {
            if (tFreq[sCharArray[right]] == 0) {
                right++;
                continue;
            }
            if (winFreq[sCharArray[right]] < tFreq[sCharArray[right]]) {
                match++;
            }
            winFreq[sCharArray[right]]++;
            right++;

            while (match == tLen) {
                if (right - left < minLen) {
                    minLen = right - left;
                    begin = left;
                }

                if (tFreq[sCharArray[left]] == 0) {
                    left++;
                    continue;
                }

                if (winFreq[sCharArray[left]] == tFreq[sCharArray[left]]) {
                    match--;
                }

                winFreq[sCharArray[left]]--;
                left++;
            }
        }

        if (minLen == sLen + 1) {
            return "";
        }

        return s.substring(begin, begin + minLen);
    }
}

// 使用哈希Map来记录t中字母还剩几次需要被匹配，当次数为0时，则matchCount++。
// 当次数为负数和正数时，matchCount不变
// 当matchCount == map.size()的时候
// 就可以将左边界向右移动了
class Solution {
    public String minWindow(String s, String t) {
         if (s.length() < t.length())
             return "";
        
        Map<Character, Integer> wordDict = constructWordDict(t);
        
        int left = 0, minLen = Integer.MAX_VALUE, matchCount = 0, index = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            Integer count = wordDict.get(ch);
            if (count == null)
                continue;
            wordDict.put(ch, --count);
            if (count == 0)
                matchCount++;
            while (matchCount == wordDict.size()) {
                if (right - left + 1 < minLen) {
                    minLen = right -left + 1;
                    index = left;
                }
                char leftest = s.charAt(left++);
                Integer leftestCount = wordDict.get(leftest);
                if (leftestCount == null)
                    continue;
                wordDict.put(leftest, ++leftestCount);
                if (leftestCount == 1)
                    matchCount--;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(index, index + minLen);
    }
    
    private Map<Character, Integer> constructWordDict(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            Integer count = map.get(ch);
            if (count == null) {
                map.put(ch, 1);
            } else {
                map.put(ch, count + 1);
            }
        }
        return map;
    }
}