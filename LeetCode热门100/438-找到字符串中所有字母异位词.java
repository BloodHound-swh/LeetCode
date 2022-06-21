/**
 * 438. 找到字符串中所有字母异位词
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。

 

示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 

提示:

1 <= s.length, p.length <= 3 * 10^4
s 和 p 仅包含小写字母

 */


// https://leetcode.cn/problems/find-all-anagrams-in-a-string/solution/hua-dong-chuang-kou-tong-yong-si-xiang-jie-jue-zi-/
// 子串类型一般都是使用滑动窗口
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        // 用map存储目标值中各个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 用另外一个map存储滑动窗口中有效字符出现的次数
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = p.length();

        while (right < s.length()) {
            // 如果目标子串中包含了该字符，才存入window中
            char cR = s.charAt(right);
            if (map.containsKey(cR)) {
                // 窗口中该字符出现次数加1
                window.put(cR, window.getOrDefault(cR, 0) + 1);
                // 只有当window中该有效字符数量不大于map中该字符数量，才能算一次有效包含
                if (window.get(cR) <= map.get(cR)) {
                    valid--;
                }
            }

            // 如果valid符合要求，就可以移动左指针了
            // 但是只有两个map存储的数据完全相同，本题里可等同于字符串长度相同，才可以记录当前的起始索引，也就是left指针所在位置
            while (valid == 0) {
                if (right - left + 1 ==  p.length()) {
                    res.add(left);
                }
                char cL = s.charAt(left);
                if (map.containsKey(cL)) {
                    window.put(cL, window.getOrDefault(cL, 0) - 1);
                    if (window.get(cL) < map.get(cL)) {
                        valid++;
                    }
                }
                left++;
            }

            right++;
        }

        return res;
    }
}