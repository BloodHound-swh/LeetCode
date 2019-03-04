/*
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


// 使用l, r来记录需要删除几个左括号和右括号
// 使用DFS，来遍历所有的括号删除情况，并用valid判定结果是否合法
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

// 仅供参考
/**
 * 这种解法首先统计了多余的半括号的数量，用cnt1表示多余的左括号，cnt2表示多余的右括号，因为给定字符串左右括号要么一样多，要么左括号多，要么右括号多，也可能左右括号都多，比如")("。
 * 所以cnt1和cnt2要么都为0，要么都大于0，要么一个为0，另一个大于0。
 * 好，下面进入我们的递归函数，首先判断，如果当cnt1和cnt2都为0时，说明此时左右括号个数相等了，我们调用isValid子函数来判断是否正确，正确的话加入结果res中并返回即可。
 * 否则从start开始遍历，这里的变量start表示当前递归开始的位置，我们不需要每次都从头开始，会有大量重复计算。
 * 而且对于多个相同的半括号在一起，我们只删除第一个，比如"())"，这里有两个右括号，我们不管删第一个还是删第二个右括号都会得到"()"，没有区别，所以只用算一次就行了.
 * 我们通过和上一个字符比较，如果不相同，说明是第一个右括号，如果相同则直接跳过。
 * 此时来看如果cnt1大于0，说明此时左括号多，而如果当前字符正好是左括号的时候，我们可以删掉当前左括号，继续调用递归，此时cnt1的值就应该减1，因为已经删掉了一个左括号。
 * 同理，如果cnt2大于0，说明此时右括号多，而如果当前字符正好是右括号的时候，我们可以删掉当前右括号，继续调用递归，此时cnt2的值就应该减1，因为已经删掉了一个右括号。
 */
public class Solution {
    char[][] patterns = {{'(', ')'}, {')', '('}};
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, 0, patterns[0], res);
        return res;
    }
    
    private void dfs(String s, int start, int lastRemove, char[] pattern, List<String> res) {
        int cnt = 0, n = s.length();
        for (int i = start; i < n; ++i) {
            if (s.charAt(i) == pattern[0]) {
                cnt++;
            }
            if (s.charAt(i) == pattern[1]) {
                cnt--;
            }
            
            if (cnt < 0) {
                for (int j = lastRemove; j <= i; ++j) {
                    if (s.charAt(j) == pattern[1] && (j == lastRemove || s.charAt(j - 1) != s.charAt(j))) {
                        dfs(s.substring(0, j) + s.substring(j + 1), i, j, pattern, res);
                    }
                }
                return;
            }
        }
        s = new StringBuilder(s).reverse().toString();
        if (pattern[0] == patterns[0][0]) {
            dfs(s, 0, 0, patterns[1], res);
        } else {
            res.add(s);
        }
    }
}
