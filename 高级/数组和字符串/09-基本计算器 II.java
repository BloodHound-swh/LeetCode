/**
 *  基本计算器 II
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



// 因为乘法和除法不仅要知道下一个数，也要知道上一个数。
// 所以我们用一个栈把上次的数存起来，遇到加减法就直接将数字压入栈中，遇到乘除法就把栈顶拿出来乘或除一下新数，再压回去。
// 最后我们把栈里所有数加起来就行了。
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0)
            return 0;
        s = s.replace(" ", "");
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
            }
            if (!Character.isDigit(s.charAt(i)) || i == s.length() - 1) { // 注意最后一位的情况
                if (sign == '+')
                    stack.push(num);
                if (sign == '-')
                    stack.push(-num);
                if (sign == '*')
                    stack.push(stack.pop() * num);
                if (sign == '/')
                    stack.push(stack.pop() / num);
                sign = s.charAt(i);
                num = 0;
            }
        }
        for (int i : stack) {
            res += i;
        }
        return res;
    }
}

// 方法一的另一种写法
public class Solution {
    public int calculate(String s) {
        s = s.replace(" ", "");
        Stack<Long> stk = new Stack<Long>();
        String firstNum = getNum(0, s);
        stk.push(Long.parseLong(firstNum));
        int i = firstNum.length();
        while (i < s.length()) {
            char c = s.charAt(i);
            // 拿出下一个数字
            String numStr = getNum(i + 1, s);
            if (c == '+') {
                stk.push(Long.parseLong(numStr));
            }
            if (c == '-') {
                stk.push(-Long.parseLong(numStr));
            }
            if (c == '*') {
                stk.push(stk.pop() * Long.parseLong(numStr));
            }
            if (c == '/') {
                stk.push(stk.pop() / Long.parseLong(numStr));
            }
            i = i + numStr.length() + 1;
        }
        long res = 0;
        while (!stk.isEmpty()) {
            res += stk.pop();
        }
        return (int) res;
    }

    private String getNum(int i, String s) {
        StringBuilder num = new StringBuilder();
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            num.append(s.charAt(i));
            i++;
        }
        return num.toString();
    }

}

// 因为没有括号，其实我们也可以不用栈。首先维护一个当前的结果，加减法的时候，直接把下一个数加上或减去就行了。
// 比如2+3*4，当算完3时，结果是5，当算到4时，先用5-3=2，再用2+3*4=14，就是当前结果。
// 这里要注意的是，对于下一个数，它的上一个数不是我们这轮的数，而是我们这轮的上轮的数乘以或除以这轮的数，如2+3*4*5，到4的时候结果14，到5的时候，上一个数是3*4，而不是4。
public class Solution {
    public int calculate(String s) {
        s = s.replace(" ", "");
        long currRes = 0, prevNum = 0;
        // 拿出第一个数
        String firstNum = getNum(0, s);
        currRes = Long.parseLong(firstNum);
        prevNum = currRes;
        int i = firstNum.length();
        while (i < s.length()) {
            char c = s.charAt(i);
            String numStr = getNum(i + 1, s);
            System.out.println(numStr);
            long n = Long.parseLong(numStr);
            if (c == '+') {
                currRes += n;
                prevNum = n;
            }
            if (c == '-') {
                currRes -= n;
                prevNum = -n;
            }
            if (c == '*') {
                // 上次的结果，减去上次的数，再加上上次的数乘以这次的数，就是这次的结果
                currRes = currRes - prevNum + prevNum * n;
                prevNum = prevNum * n;
            }
            if (c == '/') {
                // 上次的结果，减去上次的数，再加上上次的数除以这次的数，就是这次的结果
                currRes = currRes - prevNum + prevNum / n;
                prevNum = prevNum / n;
            }
            // 计算完后，跳过当前的运算符和数字
            i = i + numStr.length() + 1;
        }
        return (int) currRes;
    }

    private String getNum(int i, String s) {
        StringBuilder num = new StringBuilder();
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            num.append(s.charAt(i));
            i++;
        }
        return num.toString();
    }

}

// 方法二的另一种写法
class Solution {
    public int calculate(String s) {
        int res = 0, pre = 0, curr = 0;
        char sign = '+';
        char[] array = s.trim().toCharArray();
        for (int i = 0; i <= array.length; i++) {
            if (i != array.length && Character.isDigit(array[i]))
                curr = curr * 10 + array[i] - '0';
            else {
                if (i != array.length && array[i] == ' ')
                    continue;
                if (sign == '+') {
                    res += curr;
                    pre = curr;
                }
                if (sign == '-') {
                    res -= curr;
                    pre = -curr;
                }
                if (sign == '*') {
                    // 之前多加了一个pre（本身包括正负），现在减去
                    res = res - pre + pre * curr;
                    pre = pre * curr;
                }
                if (sign == '/') {
                    res = res - pre + pre / curr;
                    pre = pre / curr;
                }
                curr = 0;
                if (i != array.length)
                    sign = array[i];
            }
        }
        return res;
    }
}

// 优化
class Solution {
    public int calculate(String s) {
        int res = 0, curRes = 0, num = 0, n = s.length();
        char op = '+';
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                num = num * 10 + ch - '0';
            }
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == n - 1) {
                switch (op) {
                case '+':
                    curRes += num;
                    break;
                case '-':
                    curRes -= num;
                    break;
                case '*':
                    curRes *= num;
                    break;
                case '/':
                    curRes /= num;
                    break;
                }
                if (ch == '+' || ch == '-' || i == n - 1) {
                    res += curRes;
                    curRes = 0;
                }
                op = ch;
                num = 0;
            }
        }
        return res;
    }
}