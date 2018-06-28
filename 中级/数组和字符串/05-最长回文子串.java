


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