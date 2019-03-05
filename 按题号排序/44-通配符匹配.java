/**
 * 通配符匹配

给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
示例 3:

输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
示例 4:

输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
示例 5:

输入:
s = "acdcb"
p = "a*c?b"
输入: false
 */


// 没什么思路。。。


// 动态规划
// 使用match矩阵，match[i][j]表示p的前j个字符是否与s的前i个字符相匹配
// 初始化时match[0][0] = true，match[0][j] = false(i不等于0),如果p[i - 1]为*则match[i][0]与match[i - 1][0]相同
// 之后再循环过程中，如果match（包括'?'）则与左上角相同
// 如果有*，则与左边或者上边相同（左边或者上边任意有一个true则为true）
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[p.length() + 1][s.length() + 1];
        match[0][0] = true;
        
        for (int pi = 1; pi <= p.length(); pi++) {
            if (p.charAt(pi - 1) == '*')
                match[pi][0] = match[pi - 1][0];
        }
        
        for (int pi = 1; pi <= p.length(); pi++) {
            for (int si = 1; si <= s.length(); si++) {
                if (p.charAt(pi - 1) == s.charAt(si - 1) || p.charAt(pi - 1) == '?') 
                    match[pi][si] = match[pi - 1][si - 1];
                else if (p.charAt(pi - 1) == '*')
                    match[pi][si] = match[pi - 1][si] || match[pi][ si - 1];
            }
        }
        
        return match[p.length()][s.length()];
    }
}