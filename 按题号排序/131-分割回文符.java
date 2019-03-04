/**
 * 分割回文串

给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。

示例:

输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
 */

// 未看答案没有做出，关键在于如何递归，如何回溯（去除最后一个结果重新递归）

// 答案
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }

        ArrayList<String> tmp = new ArrayList<>();
        helper(s, 0, tmp, res);

        return res;
    }

    public void helper(String s, int start, ArrayList<String> tmp, List<List<String>> res) {
        if (start == s.length()) {
            ArrayList<String> list = new ArrayList<>(tmp);
            res.add(list);
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (isPalindrome(str)) {
                tmp.add(str);
                helper(s, i, tmp, res);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}