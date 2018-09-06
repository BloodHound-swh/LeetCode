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

// 动态规划
// 使用match矩阵，match[i][j]表示p的前j个字符是否与s的前i个字符相匹配
// 初始化时match[0][0] = true，match[i][0] = false(i不等于0)，match[0][j]如果p[j - 1]为*则与match[0][j - 1]相同
// 之后再循环过程中，如何match（包括？）则与左上角相同
// 如果有*，则与左边或者上边相同（左边或者上边任意有一个true则为true）
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        for (int pi = 1; pi <= p.length(); pi++) {
            if (p.charAt(pi - 1) == '*')
                match[0][pi] = match[0][pi - 1];
        }

        for (int si = 1; si <= s.length(); si++) {
            for (int pi = 1; pi <= p.length(); pi++) {
                if (p.charAt(pi - 1) == s.charAt(si - 1) || p.charAt(pi - 1) == '?')
                    match[si][pi] = match[si - 1][pi - 1];
                else if (p.charAt(pi - 1) == '*')
                    match[si][pi] = match[si - 1][pi] || match[si][pi - 1];
            }
        }
        return match[s.length()][p.length()];
    }
}

// 双指针法
class Solution {
    public boolean isMatch(String s, String p) {
        int idxs = 0, idxp = 0, idxstar = -1, idxmatch = 0;
        while (idxs < s.length()) {
            // 当两个指针指向完全相同的字符时，或者p中遇到的是?时
            if (idxp < p.length() && (s.charAt(idxs) == p.charAt(idxp) || p.charAt(idxp) == '?')) {
                idxp++;
                idxs++;
                // 如果字符不同也没有?，但在p中遇到是*时，我们记录下*的位置，但不改变s的指针
            } else if (idxp < p.length() && p.charAt(idxp) == '*') {
                idxstar = idxp;
                idxp++;
                // 遇到*后，我们用idxmatch来记录*匹配到的s字符串的位置，和不用*匹配到的s字符串位置相区分
                idxmatch = idxs;
                // 如果字符不同也没有?，p指向的也不是*，但之前已经遇到*的话，我们可以从idxmatch继续匹配任意字符
            } else if (idxstar != -1) {
                // 用上一个*来匹配，那我们p的指针也应该退回至上一个*的后面（p>=p.length()时，也会回位了)
                idxp = idxstar + 1;
                // 用*匹配到的位置递增
                idxmatch++;
                // s的指针退回至用*匹配到位置
                idxs = idxmatch;
            } else {
                return false;
            }
        }
        // 因为1个*能匹配无限序列，如果p末尾有多个*，我们都要跳过
        while (idxp < p.length() && p.charAt(idxp) == '*') {
            idxp++;
        }
        // 如果p匹配完了，说明匹配成功
        return idxp == p.length();
    }
}