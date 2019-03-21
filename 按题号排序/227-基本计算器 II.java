/**
 * 基本计算器 II

实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:

输入: "3+2*2"
输出: 7
示例 2:

输入: " 3/2 "
输出: 1
示例 3:

输入: " 3+5 / 2 "
输出: 5
说明：

你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。
 */

// 这类题基本上都要用到栈


// 因为乘法和除法不仅要知道下一个数，也要知道上一个数。
// 所以我们用一个栈把上次的数存起来，当前数的上一个符号加减法就直接将数字压入栈中（减号就压入相反数）
// 上一个符号是乘除法就把栈顶拿出来乘或除一下新数，再压回去。并且更新符号。
// 最后我们把栈里所有数加起来就行了。
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        char sign = '+';
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                num = num * 10 + ch - '0';
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == s.length() - 1) {
                if (sign == '+')
                    stack.push(num);
                if (sign == '-')
                    stack.push(-num);
                if (sign == '*')
                    stack.push(stack.pop() * num);
                if (sign == '/')
                    stack.push(stack.pop() / num);

                sign = ch;
                num = 0;
            }
        }

        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}