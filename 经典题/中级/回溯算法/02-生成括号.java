/**
 * 生成括号
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */



class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if (n == 0)
            return res;
        helper(res, "", n, n);
        return res;
    }

    public static void helper(List<String> res, String s, int left, int right) {
        if (left > right) // 此时右括号多余左括号
            return;
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) {
            helper(res, s + "(", left - 1, right);
        }
        if (right > 0) {
            helper(res, s + ")", left, right - 1);
        }
    }
}

// 直观上更符合逻辑，不会出现右括号多余左括号的情况
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<String>();
        helper("", res, n, 0, 0);
        return res;
    }

    public void helper(String curr, List<String> res, int n, int left, int right) {
        if (right == n) {
            res.add(curr);
            return;
        }
        if (left < n) {
            helper(curr + "(", res, n, left + 1, right);
        }
        if (right < left) {
            helper(curr + ")", res, n, left, right + 1);
        }
    }
}