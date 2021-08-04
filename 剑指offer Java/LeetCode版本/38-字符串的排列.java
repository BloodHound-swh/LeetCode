/**
 * 剑指 Offer 38. 字符串的排列
输入一个字符串，打印出该字符串中字符的所有排列。

 

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 

示例:

输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
 

限制：

1 <= s 的长度 <= 8
 */


// 回溯法
class Solution {
    public String[] permutation(String s) {
        // 利用set来去重
        Set<String> res = new HashSet<>();
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        char[] ch = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[ch.length];
        helper(res, ch, visited, sb);
        return res.toArray(new String[res.size()]);
    }

    public void helper(Set<String> res, char[] ch, boolean[] visited, StringBuilder sb) {
        if (sb.length() == ch.length) {
            res.add(sb.toString());
        }

        for (int i = 0; i < ch.length; i++) {
            if (visited[i] == true) {
                continue;
            }
            visited[i] = true;
            sb.append(ch[i]);
            helper(res, ch, visited, sb);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
}

// 官方题解
// 在重复的字符较多的情况下，该递归函数会生成大量重复的排列
// 我们只要在递归函数中设定一个规则，保证在填每一个空位的时候重复字符只会被填入一次。
// 具体地，我们首先对原字符串排序，保证相同的字符都相邻，在递归函数中，我们限制每次填入的字符一定是这个字符所在重复字符集合中「从左往右第一个未被填入的字符」
class Solution {
    List<String> rec;
    boolean[] vis;

    public String[] permutation(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backtrack(arr, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }

    public void backtrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            rec.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (vis[j] || (j > 0 && !vis[j - 1] && arr[j - 1] == arr[j])) {
                continue;
            }
            vis[j] = true;
            perm.append(arr[j]);
            backtrack(arr, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }
}