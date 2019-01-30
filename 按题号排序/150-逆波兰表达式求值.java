/**
 * 逆波兰表达式求值

根据逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
示例 1：

输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9
示例 2：

输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: (4 + (13 / 5)) = 6
示例 3：

输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */

// 未看答案是的思路是对的，但是在细节处，例如如何判断tokens[i]是数字还是运算符应该怎样写不太清楚。另外，switch语法需要熟悉

// 未看答案版(有查找资料)
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        String operators = "+-*/";
        for (int i = 0; i < tokens.length; i++) {
            if (!operators.contains(tokens[i])) {
                stack.push(Integer.valueOf(tokens[i]));
                continue;
            }
            int a = stack.pop();
            int b = stack.pop();

            switch (tokens[i]) {
            case "+":
                stack.push(b + a);
                break;
            case "-":
                stack.push(b - a);
                break;
            case "*":
                stack.push(b * a);
                break;
            case "/":
                stack.push(b / a);
                break;
            }
        }
        return stack.pop();
    }
}

// 答案一
// 使用stack来解决，注意本题不考虑括号
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        String operators = "+-*/";
        for (String token : tokens) {
            if (!operators.contains(token)) {
                s.push(Integer.valueOf(token));
                continue;
            }
            int a = s.pop();
            int b = s.pop();
            if (token.equals("+")) {
                s.push(b + a);
            } else if (token.equals("-")) {
                s.push(b - a);
            } else if (token.equals("*")) {
                s.push(b * a);
            } else {
                s.push(b / a);
            }
        }
        return s.pop();
    }
}

// 把if else改成switch
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < tokens.length; i++) {
            String cur = tokens[i];
            switch (cur) {
            case "+":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num1 + num2);
                break;
            case "-":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 - num1);
                break;
            case "*":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num1 * num2);
                break;
            case "/":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 / num1);
                break;
            default:
                stack.push(Integer.parseInt(cur)); // 可以看看Integre.parseInt 和 Integer.valueOf() 的区别
            }

        }
        return stack.pop();
    }
}