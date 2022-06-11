/**
 * 301. 删除无效的括号
给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。

 

示例 1：

输入：s = "()())()"
输出：["(())()","()()()"]
示例 2：

输入：s = "(a)())()"
输出：["(a())()","(a)()()"]
示例 3：

输入：s = ")("
输出：[""]
 

提示：

1 <= s.length <= 25
s 由小写英文字母以及括号 '(' 和 ')' 组成
s 中至多含 20 个括号
 */


// 注意到题目中要求最少删除，这样的描述正是广度优先搜索算法应用的场景
// 在进行广度优先搜索时每一轮删除字符串中的 11 个括号，直到出现合法匹配的字符串为止不在进行下一轮，此时进行轮转的次数即为最少需要删除括号的个数
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<String>();
        Set<String> currSet = new HashSet<String>();

        currSet.add(s);
        while (true) {
            for (String str : currSet) {
                if (isValid(str)) {
                    ans.add(str);
                }
            }
            if (ans.size() > 0) {
                return ans;
            }
            Set<String> nextSet = new HashSet<String>();
            // for (String str : currSet) {
            //     for (int i = 0; i < str.length(); i ++) {
            //         if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
            //             continue;
            //         }
            //         if (str.charAt(i) == '(' || str.charAt(i) == ')') {
            //             nextSet.add(str.substring(0, i) + str.substring(i + 1));
            //         }
            //     }
            // }
            for (String str : currSet) {
                StringBuilder sb = new StringBuilder(str);
                for (int i = 0; i < str.length(); i ++) {
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                        continue;
                    }
                    if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                        sb.deleteCharAt(i);
                        nextSet.add(sb.toString());
                        sb.insert(i, str.charAt(i));
                    }
                }
            }
            currSet = nextSet;
        }
    }

    private boolean isValid(String str) {
        char[] ss = str.toCharArray();
        int count = 0;

        for (char c : ss) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}