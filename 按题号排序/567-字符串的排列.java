/**
 * 字符串的排列
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").
 

示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False
 

注意：

输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间
 */


// 使用两个数组和滑动窗口。因为都是小写字母，所以用数组就很方便。
// 用数组记录字母出现的次数就可以省去求全排列的痛苦了。
// 只要滑动窗口中的counts2数组与counts1数组equals，就返回true。
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0)
            return true;
        if (s2 == null || s2.length() == 0)
            return false;
        if (s1.length() > s2.length())
            return false;

        int[] counts1 = new int[26];
        int[] counts2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            counts1[s1.charAt(i) - 'a']++;
            counts2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(counts1, counts2))
            return true;

        for (int i = s1.length(); i < s2.length(); i++) {
            counts2[s2.charAt(i) - 'a']++;
            counts2[s2.charAt(i - s1.length()) - 'a']--;
            if (Arrays.equals(counts1, counts2))
                return true;
        }

        return false;

    }
}