/*
 * 正则表达式匹配
给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符。
'*' 匹配零个或多个前面的元素。
匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
 */

// 同上一题，使用match[][]矩阵
// 初始化时，match[0][i]如果是*则与上两个的值相同
// 循环时，依然是分为match（或者为.而不是*）的情况与为*的情况，以及不match的情况来讨论
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                match[0][i] = match[0][i - 2];
            }
        }

        for (int si = 1; si <= s.length(); si++) {
            for (int pi = 1; pi <= p.length(); pi++) {
                if (p.charAt(pi - 1) == '.' || p.charAt(pi - 1) == s.charAt(si - 1)) {
                    match[si][pi] = match[si - 1][pi - 1];
                } else if (p.charAt(pi - 1) == '*') {
                    if (p.charAt(pi - 2) == s.charAt(si - 1) || p.charAt(pi - 2) == '.') {
                        match[si][pi] = match[si][pi - 2] || match[si - 1][pi];
                    } else {
                        match[si][pi] = match[si][pi - 2];
                    }
                }
            }
        }
        return match[s.length()][p.length()];
    }
}

// 方法二不是很懂，大概解释一下
/**
 * 我们举个例子
 * 
 * match: 0 0 0 1 string: a a b pattern: a * b |
 * 这里我们先看pattern最后一位b能匹配到多少，这里因为b不是星号，
 * 所以我们从左往右尝试匹配string，第一个a不行，第二个a也不行，然后到b，这里因为match[3]是true，b也和b相同，所以匹配成功。
 * 
 * match: 0 0 1 1 string: a a b pattern: a * b |
 * 然后看pattern的这个星号，我们要从后往前匹配string。因为b已经被匹配了，match[2]是true，所以直接跳过。
 * 然后到a，发现个pattern中星号前面的字符a相同，所以匹配成功，match[1]也置为true再看string的第一个a，还是可以匹配成功，这样整个string都被匹配成功了。
 * 
 * 这里还有几个情况，首先，无论刚才那pattern中最后一个b有没有匹配到string中任何一个字符，match[3]也要置为false。
 * 这样才能防止pattern最后字母没有匹配上，而pattern前面的部分反而把string的结尾给匹配了。还有如果pattern中是句号的话，那相当于字符相同。
 * 
 * 
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        boolean[] match = new boolean[s.length() + 1];
        match[s.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) == '*') {
                // 如果是星号，从后往前匹配
                for (int j = s.length() - 1; j >= 0; j--) {
                    match[j] = match[j]
                            || (match[j + 1] && (p.charAt(i - 1) == '.' || (p.charAt(i - 1) == s.charAt(j))));
                }
                // 记得把i多减一，因为星号是和其前面的字符配合使用的
                i--;
            } else {
                // 如果不是星号，从前往后匹配
                for (int j = 0; j < s.length(); j++) {
                    match[j] = match[j + 1] && (p.charAt(i) == '.' || (p.charAt(i) == s.charAt(j)));
                }
                // 只要试过了pattern中最后一个字符，就要把match[s.length()]置为false
                match[s.length()] = false;
            }
        }
        return match[0];
    }
}
