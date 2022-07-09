/**
 * 647. 回文子串
给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。

回文字符串 是正着读和倒过来读一样的字符串。

子字符串 是字符串中的由连续字符组成的一个序列。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

 

示例 1：

输入：s = "abc"
输出：3
解释：三个回文子串: "a", "b", "c"
示例 2：

输入：s = "aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 

提示：

1 <= s.length <= 1000
s 由小写英文字母组成
 */


// 暴力解法
class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isHuiwen(s.substring(i, j + 1))) {
                    res++;
                }
            }
        }

        return res;
    }

    public boolean isHuiwen(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }

        if (str.length() == 1) {
            return true;
        }

        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

// 使用dp数组节省时间复杂度
// dp[i][j]表示字符串下标i到j之间的子字符串是否为回文子串
class Solution {
    public int countSubstrings(String s) {
        // 动态规划法
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }

        return ans;
    }
}

// 中心扩展法
class Solution {
    
    private int ans = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            // 以单个字母为中心的情况
            isPalindromic(s, i, i);
            // 以两个字母为中心的情况
            isPalindromic(s, i, i + 1);
        }
        return ans;
    }
    
    private void isPalindromic(String s, int i, int j) {
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j)) {
                return;
            }
            i--;
            j++;
            ans++;
        }
    }
}