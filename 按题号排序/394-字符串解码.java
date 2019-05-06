/**
 * 字符串解码
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

示例:

s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 */


// 使用两个栈，一个记录数字，一个记录字符串
// 如果遇到数字，更新计数变量，如果遇到左中括号，把当前数字压入数字栈中
// 如果遇到右中括号，取出数字栈的栈顶元素，存入times，然后给字符串的栈顶元素循环加上times个括号里的字符串
class Solution {
    public String decodeString(String s) {
        char[] ch = s.toCharArray();
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();

        int cnt = 0;
        String res = "";

        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (c >= '0' && c <= '9') {
                cnt = 10 * cnt + (c - '0');
            } else if (c == '[') {
                countStack.push(cnt);
                resStack.push(res);
                cnt = 0;
                res = "";
            } else if (c == ']') {
                String tmp = "";
                int times = countStack.pop();
                for (int j = 0; j < times; j++) {
                    tmp += res;
                }
                res = resStack.pop() + tmp;
            } else {
                res += c;
            }
        }

        return res;
    }
}