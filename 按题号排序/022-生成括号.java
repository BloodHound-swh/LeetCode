/**
 * 括号生成

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


// 未看答案没有做出，对于递归的边界情况无法判定

// 答案一
// 我们定义两个变量left和right分别表示剩余左右括号的个数.
// 如果在某次递归时，左括号的个数大于右括号的个数，说明此时生成的字符串中右括号的个数大于左括号的个数，即会出现')('这样的非法串，所以这种情况直接返回，不继续处理。
// 如果left和right都为0，则说明此时生成的字符串已有3个左括号和3个右括号，且字符串合法，则存入结果中后返回。
// 如果以上两种情况都不满足，若此时left大于0，则调用递归函数，注意参数的更新，若right大于0，则调用递归函数
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

// 答案二
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