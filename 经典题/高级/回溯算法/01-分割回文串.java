/*
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

// 使用递归算法
// 先按顺序依次判断是否为回文串，知道到达最后，返回第一个temp；
// 将partition的最后一个回文串remove，回溯，看看改变切割方法是否还是回文串
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();

        if (s == null || s.length() == 0) {
            return result;
        }

        ArrayList<String> partition = new ArrayList<String>();// track each possible partition
        addPalindrome(s, 0, partition, result);

        return result;
    }

    private void addPalindrome(String s, int start, ArrayList<String> partition, List<List<String>> result) {
        // 终止条件
        if (start == s.length()) {
            ArrayList<String> temp = new ArrayList<String>(partition);
            result.add(temp);
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (isPalindrome(str)) {
                partition.add(str);
                addPalindrome(s, i, partition, result);
                partition.remove(partition.size() - 1); // 回溯
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

// 方法一就是参考（抄）这个老外写出来的
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> rst = new ArrayList<List<String>>();
        if (s == null || s.length() < 0) {
            return rst;
        }
        ArrayList<String> path = new ArrayList<String>();
        helper(s, path, 0, rst);
        return rst;
    }

    // Check Palindrome
    public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // helper:
    public void helper(String s, ArrayList<String> path, int pos, List<List<String>> rst) {
        if (pos == s.length()) {
            rst.add(new ArrayList<String>(path));
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {// i is used in s.sbustring(pos, i), which can equal to s.length()
            String prefix = s.substring(pos, i);
            if (!isPalindrome(prefix)) {
                continue;
            }
            path.add(prefix);
            helper(s, path, i, rst);
            path.remove(path.size() - 1);
        }
    }
}

// 优化，可以再每一个dfs level 算 isPalindrome(S), 但是可以先把 boolean[][] isPalin 算出来, 每次O(1)
// 来用
// -注意: isPalin[i][j] 是 inclusive的, 所以用的时候要认准坐标
class Solution {
    boolean[][] isPalin;
    String str;

    public List<List<String>> partition(String s) {
        List<List<String>> rst = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return rst;
        }
        isPalin = calcPalin(s);
        str = s;
        dfs(rst, new ArrayList<>(), 0);
        return rst;
    }

    private void dfs(List<List<String>> rst, List<String> list, int index) {
        if (x == str.length()) {
            rst.add(new ArrayList<>(list));
            return;
        }
        for (int i = index + 1; i <= str.length(); i++) {
            if (isPalin[index][i - 1]) { // 也需要查看自身是不是 palindrome: s.charAt(x). isPalin[i][j] 是 inclusive的
                list.add(str.substring(index, i));
                dfs(rst, list, i);
                list.remove(list.size() - 1);
            }
        }
    }

    // Kinda DP, isPalin[i][j] shows palindrome status for s[i,j] inclusivly
    private boolean[][] calcPalin(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        boolean[][] isPalin = new boolean[n][n];
        int i, j;

        for (int mid = 0; mid < n; mid++) {
            // odd: single char in center
            i = j = mid;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                isPalin[i][j] = true;
                i--;
                j++;
            }

            // even: always even number of palindrome characters
            i = mid;
            j = mid + 1;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                isPalin[i][j] = true;
                i--;
                j++;
            }
        }
        return isPalin;
    }
}