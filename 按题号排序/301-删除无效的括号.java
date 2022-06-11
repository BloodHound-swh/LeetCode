/**
 * 删除无效的括号

删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。

说明: 输入可能包含了除 ( 和 ) 以外的字符。

示例 1:

输入: "()())()"
输出: ["()()()", "(())()"]
示例 2:

输入: "(a)())()"
输出: ["(a)()()", "(a())()"]
示例 3:

输入: ")("
输出: [""]
 */


// 未看答案，只想到了如何判定当前的括号是不对的，但是应该如何修改，如何回溯没有思路


// 答案
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> results = new LinkedList<>();
        if (s == null)
            return results;
        int n = s.length();
        int l = 0;
        int r = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                l++;
            }
            if (s.charAt(i) == ')') {
                if (l > 0) {
                    l--;
                } else {
                    r++;
                }
            }
        }

        dfs(new StringBuilder(s), 0, l, r, results);
        return results;
    }

    public void dfs(StringBuilder sb, int index, int l, int r, List<String> results) {
        if (l == 0 && r == 0) { // 不能少，因为即便是r = l = 0, 可能括号的顺序是反的
            if (isValid(sb)) {
                results.add(sb.toString());
            }
            return;
        }

        for (int i = index; i < sb.length(); i++) {
            if (i > index && sb.charAt(i) == sb.charAt(i - 1)) { // 对于连续重复的符号，只需记录删除第一个的情况就可以了
                continue;
            }

            if (sb.charAt(i) == '(' && l > 0) {
                sb.deleteCharAt(i);
                dfs(sb, i, l - 1, r, results);
                sb.insert(i, '('); // 回溯
            }

            if (sb.charAt(i) == ')' && r > 0) {
                sb.deleteCharAt(i);
                dfs(sb, i, l, r - 1, results);
                sb.insert(i, ')');
            }
        }
    }

    public boolean isValid(StringBuilder sb) {
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                count++;
            }
            if (sb.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}

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