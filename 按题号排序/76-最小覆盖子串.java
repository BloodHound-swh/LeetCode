/**
 * 最小覆盖子串

给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。

示例：

输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */


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