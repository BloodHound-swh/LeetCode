/*
 * 字符串转整数 (atoi)

实现 atoi，将字符串转为整数。

该函数首先根据需要丢弃任意多的空格字符，直到找到第一个非空格字符为止。如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。

当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。

若函数不能执行有效的转换，返回 0。

说明：

假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。

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
     因此返回 INT_MIN (−2^31) 。
 */

// 未看答案版，不正确，在出现+后面紧接着-号时本应该输出0，但依然按照运算法则计算了(很坑,不符合常理)
class Solution {
    public int myAtoi(String str) {
        if (str == null)
            return 0;
        char[] c = str.toCharArray();
        long res = 0;
        int flag = 1;
        int i = 0;
        for (i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                continue;
            } else if (c[i] == '+') {
                continue;
            } else if (c[i] == '-') {
                flag = -1;
            } else if (c[i] >= '0' && c[i] <= '9') {
                res = 10 * res + (c[i] - '0');
                if (res > Integer.MAX_VALUE)
                    break;
            } else {
                break;
            }
        }
        if (res * flag > Integer.MAX_VALUE)
            return Integer.MAX_VALUE * flag;
        if (res * flag < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int) res * flag;
    }
}

// 答案一，注意去除空格的函数
// 主要使用charAt(index)判定是否转换以及转换范围。
class Solution {
    public int myAtoi(String str) {
        if (str == null)
            return 0;
        str = str.trim(); // 去除空格
        if (str.length() == 0)
            return 0;
        int flag = 1;
        int index = 0;
        // 保证+，-在开头index = 0只能出现一遍
        if (str.charAt(index) == '+')
            index++;
        else if (str.charAt(index) == '-') {
            flag = -1;
            index++;
        }
        long num = 0;
        for (; index < str.length(); index++) {
            if (str.charAt(index) < '0' || str.charAt(index) > '9')
                break;
            num = num * 10 + (str.charAt(index) - '0');
            if (num > Integer.MAX_VALUE)
                break;
        }
        if (num * flag > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if (num * flag < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int) num * flag;
    }
}

// 答案二
// step1.去掉空格；
// step2.判断±号；
// step3.依次遍历，如果为0~9，则base*10加上该数字，将得到的值赋给base。若base大于214746364，或等于214746364并且该数字大于7，说明int溢出，返回2147463647或2147463648；
// step4.返回base*sign。
public class Solution {
    public int myAtoi(String str) {
        if (str.isEmpty())
            return 0;
        int sign = 1, base = 0, i = 0, n = str.length();
        while (i < n && str.charAt(i) == ' ') {
            i++;
            if (i == n) {
                return 0;
            }
        }
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

// 答案三，思想同上
class Solution {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        int res = 0;
        int i = 0;
        int flag = 1;
        if (str.charAt(i) == '-') {
            flag = -1;
        }
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            i++;
        }

        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            int r = str.charAt(i) - '0';
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + r;
            i++;
        }

        return flag > 0 ? res : -res;
    }
}
