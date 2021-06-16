/**
 * 题目描述
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */


// 同LeetCode 65题
public class Solution {
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) 
            return false;
        int i = 0;
        if (str[i] == '+' || str[i] == '-')
            i++;
        
        boolean isNum = false, isDot = false, isExp = false;
        
        for (; i < str.length; i++) {
            char c = str[i];
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
                if (i >= 1 && (str[i - 1] != 'e' && str[i - 1] != 'E'))
                    return false;
            } else {
                return false;
            }
        }
        
        return isNum;
    }
}