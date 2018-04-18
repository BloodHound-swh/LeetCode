/**
字符串转整数 (atoi)
实现 atoi，将字符串转为整数。
在找到第一个非空字符之前，函数需尽可能移除掉空格字符。然后从这个非空字符开始，选取一个可选的正号或负号，并将正号或负号与后面尽可能多的连续的数字组合起来，这部分字符即为数字的值。
字符串可以在形成整数数字的字符后面包括多余的非数字字符，将这些字符忽略，这些对于函数没有影响。
如果字符串中的第一个非空字符不是有效的整数，字符串为空，或字符串仅包含空白字符，则不进行转换。
如果不能执行有效的转换，则返回 0。
说明：
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
示例 1:
输入: "42"
输出: 42

示例 2:
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。

示例 3:
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

示例 4:
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
     
示例 5:
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
 */



//主要使用charAt(index)判定是否转换以及转换范围。
class Solution {
    public int myAtoi(String str) {
        if(str==null)
            return 0;
        str = str.trim();
        if(str.length()==0)
            return 0;
        int flag = 1;
        int index = 0;
        if(str.charAt(index)=='+')
            index++;
        else if(str.charAt(index)=='-'){
            flag = -1;
            index++;
        }
        long num = 0;
        for(;index<str.length();index++){
            if(str.charAt(index)<'0'||str.charAt(index)>'9')
                break;
            num = num * 10 + (str.charAt(index) - '0');
            if(num > Integer.MAX_VALUE)
                break;
        }
        if(num * flag > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if(num * flag < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int)num * flag;
    }
}

// step1.去掉空格；
// step2.判断±号；
// step3.依次遍历，如果为0~9，则base*10加上该数字，将得到的值赋给base。若base大于214746364，或等于214746364并且该数字大于7，说明int溢出，返回2147463647或2147463648；
// step4.返回base*sign。

public class Solution {
    public int myAtoi(String str) {
        if (str.isEmpty()) return 0;
        int sign = 1, base = 0, i = 0, n = str.length();
        while (i < n && str.charAt(i) == ' ') ++i;
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            sign = (str.charAt(i++) == '+') ? 1 : -1;
        }
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = 10 * base + (str.charAt(i++) - '0');
        }
        return base * sign;
    }
}