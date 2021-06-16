/**
 * 题目描述
请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 
在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */


// https://www.youtube.com/watch?v=DqhPJ8MzDKM
// 初始化时，match[0][i]如果是*则与上两个的值相同
// 循环时，依然是分为match（或者为.而不是*）的情况与为*的情况，以及不match的情况来讨论
public class Solution {
    public boolean match(char[] str, char[] pattern) {
        boolean[][] match = new boolean[pattern.length + 1][str.length + 1];
        match[0][0] = true;

        for (int i = 1; i <= pattern.length; i++) {
            if (pattern[i - 1] == '*') {
                match[i][0] = match[i - 2][0];
            }
        }

        for (int i = 1; i <= pattern.length; i++) {
            for (int j = 1; j <= str.length; j++) {
                if (pattern[i - 1] == str[j - 1] || pattern[i - 1] == '.')
                    match[i][j] = match[i - 1][j - 1];
                else if (pattern[i - 1] == '*') {
                    if (pattern[i - 2] == str[j - 1] || pattern[i - 2] == '.')
                        match[i][j] = match[i][j - 1] || match[i - 2][j];
                    else
                        match[i][j] = match[i - 2][j];
                }
            }
        }

        return match[pattern.length][str.length];

    }
}