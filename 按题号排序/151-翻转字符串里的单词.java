/**
 * 翻转字符串里的单词

示例 1：

输入: "the sky is blue"
输出: "blue is sky the"
示例 2：

输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
示例 3：

输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 

说明：

无空格字符构成一个单词。
输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 

进阶：

请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 */


// 我们首先将原字符串调用trim()来去除冗余空格，然后调用split()来分隔，分隔符设为"\\s+"
// 这其实是一个正则表达式，\\s表示空格字符，+表示可以有一个或多个空格字符
// 那么我们就可以把单词分隔开装入一个字符串数组中，然后我们从末尾开始，一个个把单词取出来加入结果res中，并且单词之间加上空格字符
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] words = s.trim().split("\\s+");
        for (int i = words.length - 1; i > 0; i--) {
            sb.append(words[i] + " ");
        }
        sb.append(words[0]);

        return sb.toString();
    }
}