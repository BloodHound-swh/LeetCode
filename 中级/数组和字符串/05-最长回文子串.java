/**
最长回文子串
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba"也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
 */


//使用递归，每次判定收尾是否相同以及里面的字符串是否是回文即可。
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = -1;
        
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++){
                dp[i][j] = (s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1])); //先判定j - i 因为 a, aa, aba只需要收尾相同即可
                if (dp[i][j]) {
                    if( j - i > max){
                        max = j - i;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }
}

//中心扩散法，如 a b c b a，从c开始向两边扩散，或者从 b c 开始向两边扩散
class Solution {
    public String res = "";
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        for (int i = 0; i < s.length(); i++ ) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return res;
    }
    
    public void helper(String s, int left, int right) {
        while (left >=0 && right <s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String curr = s.substring(left + 1, right);
        if(curr.length() > res.length()){
            res = curr;
        }
    }
}