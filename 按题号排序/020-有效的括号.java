/**
 * 有效的括号

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true
 */

// 看了一下提示，使用栈的方法进行匹配弹出
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek() != '(')
                    return false;
                stack.pop();
            } else if (s.charAt(i) == ']') {
                if (stack.isEmpty() || stack.peek() != '[')
                    return false;
                stack.pop();
            } else if (s.charAt(i) == '}') {
                if (stack.isEmpty() || stack.peek() != '{')
                    return false;
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

// 答案一
// 运用stack,每次出现右括号的时候,pop出stack中最近储存的左括号比较
class Solution {
    public boolean isValid(String s) {
        Stack<Character> mask = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                mask.push(s.charAt(i));
            } else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                if (mask.isEmpty())
                    return false;
                char cur = mask.pop();

                if (cur == '(' && s.charAt(i) != ')')
                    return false;
                if (cur == '[' && s.charAt(i) != ']')
                    return false;
                if (cur == '{' && s.charAt(i) != '}')
                    return false;
            }
        }
        if (mask.isEmpty())
            return true;
        return false;
    }
}

// 答案二
// 换一种思路,主动地在出现左括号时，将匹配的右括号push进stack中，在出现右括号时，与push出来的右括号比较
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}