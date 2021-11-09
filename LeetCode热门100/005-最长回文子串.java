/**
 * 5. 最长回文子串
给你一个字符串 s，找到 s 中最长的回文子串。

 

示例 1：

输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
示例 2：

输入：s = "cbbd"
输出："bb"
示例 3：

输入：s = "a"
输出："a"
示例 4：

输入：s = "ac"
输出："a"
 

提示：

1 <= s.length <= 1000
s 仅由数字和英文字母（大写和/或小写）组成
 */

// 中心扩散法，如 a b c b a，从c开始向两边扩散，或者从 b c 开始向两边扩散
class Solution {
    public String res = "";

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return s;
        for (int i = 0; i < s.length(); i++) { // 注意回文子串的字符数可能是奇数也可能是偶数
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return res;
    }

    public void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String curr = s.substring(left + 1, right);
        if (curr.length() > res.length()) {
            res = curr;
        }
    }
}

// 使用递归，每次判定首尾是否相同以及里面的字符串是否是回文即可。
public class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}

// 同上，表达更加简洁
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return s;
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = -1;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1])); // 先判定j - i 因为 a, aa, aba只需要收尾相同即可
                if (dp[i][j]) {
                    if (j - i > max) {
                        max = j - i;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }
}