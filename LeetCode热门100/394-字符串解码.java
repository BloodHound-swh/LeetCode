/**
 * 394. 字符串解码
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

 

示例 1：

输入：s = "3[a]2[bc]"
输出："aaabcbc"
示例 2：

输入：s = "3[a2[c]]"
输出："accaccacc"
示例 3：

输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"
示例 4：

输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"
 

提示：

1 <= s.length <= 30
s 由小写英文字母、数字和方括号 '[]' 组成
s 保证是一个 有效 的输入。
s 中所有整数的取值范围为 [1, 300] 
 */


// 使用两个栈，一个记录数字，一个记录字符串
// 如果遇到数字，更新计数变量，如果遇到左中括号，把当前数字压入数字栈中
// 如果遇到右中括号，取出数字栈的栈顶元素，存入times，然后给字符串的栈顶元素循环加上times个括号里的字符串
class Solution {
    public String decodeString(String s) {
        char[] ch = s.toCharArray();
        Stack<Integer> countStack = new Stack<>();
        Stack<String> strStack = new Stack<>();

        int cnt = 0;
        String str = "";

        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (c >= '0' && c <= '9') {
                cnt = cnt * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(cnt);
                strStack.push(str);
                cnt = 0;
                str = "";
            } else if (c == ']') {
                String tmp = "";
                int times = countStack.pop();
                for (int j = 0; j < times; j++) {
                    tmp  = tmp + str;
                }
                str = strStack.pop() + tmp;
            } else {
                str = str + c;
            }
        }

        return str;
    }
}