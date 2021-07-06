/**
 * 有效数字

验证给定的字符串是否可以解释为十进制数字。

例如:

"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：

数字 0-9
指数 - "e"
正/负号 - "+"/"-"
小数点 - "."
当然，在输入中，这些字符的上下文也很重要。
 */


// https://www.youtube.com/watch?v=QXNvEz-GwQ4&t=361s
// 根据上面的链接修改的答案
class Solution {
    public boolean isNumber(String s) {
        if (s == null)
            return false;
        s = s.trim();
        if (s.length() == 0)
            return false;

        int i = 0;
        char[] ch = s.toCharArray();
        if (ch[i] == '+' || ch[i] == '-')
            i++;

        boolean isNum = false, isDot = false, isExp = false;
        for (; i < ch.length; i++) {
            char c = ch[i];
            if (Character.isDigit(c)) {
                isNum = true;
            } else if (c == '.') {
                if (isDot || isExp)
                    return false;
                isDot = true;
            } else if (c == 'e' || c == 'E') {
                if (isExp || !isNum)
                    return false;
                isExp = true;
                isNum = false;
            } else if (c == '+' || c == '-') {
                if (i >= 1 && (ch[i - 1] != 'e' && ch[i - 1] != 'E'))
                    return false;
            } else {
                return false;
            }
        }

        return isNum;
    }
}