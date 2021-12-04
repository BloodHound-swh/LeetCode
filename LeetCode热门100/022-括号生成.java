/**
 * 22. 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

 

示例 1：

输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：

输入：n = 1
输出：["()"]
 

提示：

1 <= n <= 8

 */

// 我们定义两个变量left和right分别表示剩余左右括号的个数.
// 如果在某次递归时，左括号的个数大于右括号的个数，说明此时生成的字符串中右括号的个数大于左括号的个数，即会出现')('这样的非法串，所以这种情况直接返回，不继续处理。
// 如果left和right都为0，则说明此时生成的字符串已有3个左括号和3个右括号，且字符串合法，则存入结果中后返回。
// 如果以上两种情况都不满足，若此时left大于0，则调用递归函数，注意参数的更新，若right大于0，同样调用递归函数
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, n, new StringBuilder(), res);
        return res;
    }

    public void helper(int left, int right, StringBuilder sb, List<String> res) {
        if (left < 0 || right < 0 || right < left) {
            return;
        }
        if (left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }
        helper(left - 1, right, sb.append("("), res);
        sb.deleteCharAt(sb.length() - 1);
        helper(left, right - 1, sb.append(")"), res);
        sb.deleteCharAt(sb.length() - 1);
    }
}

// 注意这里用的是tmp的形式, 所以就没有必要回溯了
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, n, "", res);
        return res;
    }

    public void helper(int left, int right, String tmp, List<String> res) {
        if (left < 0 || right < 0 || right < left) {
            return;
        }
        if (left == 0 && right == 0) {
            res.add(tmp);
            return;
        }
        helper(left - 1, right, tmp + "(", res);
        helper(left, right - 1, tmp + ")", res);
    }
}

// 官方题解，回溯法
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res, 0, 0, new StringBuilder(), n);
        return res;
    }

    public void helper(List<String> res, int left, int right, StringBuilder sb, int max) {
        if (sb.length() == max * 2) {
            res.add(sb.toString());
        }

        if (left < max) {
            helper(res, left + 1, right, sb.append("("), max);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (left > right) {
            helper(res, left, right + 1, sb.append(")"), max);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}