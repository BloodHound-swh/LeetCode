/**
 *  最小窗口子字符串
给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。

示例：

输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */



// 使用tArr保存t字符串字母与出现次数的关系，sArr来记录目前s字符串中在t字符串出现的字母与次数的关系
// 使用左右指针，matchCount来记录总出现次数，当matchCount == t.length()时，存储到res中
// 移动左指针到s字符串下一个符合的字母上，再移动右指针
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0)
            return "";
        int matchCount = 0;
        String res = "";
        int[] tArr = new int[256];
        for (char c : t.toCharArray()) {
            tArr[c]++;
        }
        int[] sArr = new int[256];
        int left = findNextStrIdx(0, s, tArr);
        if (left == s.length())
            return "";
        int right = left;
        while (right < s.length()) {
            int rightChar = s.charAt(right);
            if (sArr[rightChar] < tArr[rightChar]) {
                matchCount++;
            }
            sArr[rightChar]++;
            while (left < s.length() && matchCount == t.length()) {
                if (res.isEmpty() || res.length() > right - left + 1) {
                    res = s.substring(left, right + 1);
                }
                int leftChar = s.charAt(left);
                if (sArr[leftChar] <= tArr[leftChar]) {
                    matchCount--;
                }
                sArr[leftChar]--;
                left = findNextStrIdx(left + 1, s, tArr);
            }
            right = findNextStrIdx(right + 1, s, tArr);
        }
        return res;
    }

    public int findNextStrIdx(int start, String s, int[] tArr) {
        while (start < s.length()) {
            char c = s.charAt(start);
            if (tArr[c] != 0)
                return start;
            start++;
        }
        return start;
    }
}

// 优化
class Solution {
    public String minWindow(String s, String t) {
        int[] cnt = new int[128];
        for (char c : t.toCharArray()) {
            cnt[c]++;
        }
        int from = 0;
        int total = t.length();
        int min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)]-- > 0)
                total--;
            while (total == 0) {
                if (i - j + 1 < min) {
                    min = i - j + 1;
                    from = j;
                }
                if (++cnt[s.charAt(j++)] > 0)
                    total++;
            }
        }
        return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
    }
}

// 优化二，思想同方法一
// 用一个哈希表记录目标字符串每个字母的个数，一个哈希表记录窗口中每个字母的个数。(实际上用的还是数组)
// 先找到第一个有效的窗口，用两个指针标出它的上界和下界。然后每次窗口右界向右移时，将左边尽可能的右缩，右缩的条件是窗口中字母的个数不小于目标字符串中字母的个数。
public class Solution {
    public String minWindow(String S, String T) {
        int[] srcHash = new int[255];
        // 记录目标字符串每个字母出现次数
        for (int i = 0; i < T.length(); i++) {
            srcHash[T.charAt(i)]++;
        }
        int start = 0, i = 0;
        // 用于记录窗口内每个字母出现次数
        int[] destHash = new int[255];
        int found = 0;
        int begin = -1, end = S.length(), minLength = S.length();
        for (start = i = 0; i < S.length(); i++) {
            // 每来一个字符给它的出现次数加1
            destHash[S.charAt(i)]++;
            // 如果加1后这个字符的数量不超过目标串中该字符的数量，则找到了一个匹配字符
            if (destHash[S.charAt(i)] <= srcHash[S.charAt(i)])
                found++;
            // 如果找到的匹配字符数等于目标串长度，说明找到了一个符合要求的子串
            if (found == T.length()) {
                // 将开头没用的都跳过，没用是指该字符出现次数超过了目标串中出现的次数，并把它们出现次数都减1
                while (start < i && destHash[S.charAt(start)] > srcHash[S.charAt(start)]) {
                    destHash[S.charAt(start)]--;
                    start++;
                }
                // 这时候start指向该子串开头的字母，判断该子串长度
                if (i - start < minLength) {
                    minLength = i - start;
                    begin = start;
                    end = i;
                }
                // 把开头的这个匹配字符跳过，并将匹配字符数减1
                destHash[S.charAt(start)]--;
                found--;
                // 子串起始位置加1，我们开始看下一个子串了
                start++;
            }
        }
        // 如果begin没有修改过，返回空
        return begin == -1 ? "" : S.substring(begin, end + 1);
    }
}