/**
 * 79. 单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

示例 1：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
示例 2：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true
示例 3：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
 

提示：

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board 和 word 仅由大小写英文字母组成
 

进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */


// 很常见的回溯法+DFS，使用额外的空间visited记录是否已经访问过
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper(board, visited, w, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;

    }

    public boolean helper(char[][] board, boolean[][] visited, char[] w, int idx, int i, int j) {
        if (idx == w.length) {
            return true;
        }

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }

        if (visited[i][j] == true || board[i][j] != w[idx]) {
            return false;
        }

        visited[i][j] = true;
        boolean flag = helper(board, visited, w, idx + 1, i + 1, j)
                    || helper(board, visited, w, idx + 1, i - 1, j)
                    || helper(board, visited, w, idx + 1, i, j + 1)
                    || helper(board, visited, w, idx + 1, i, j - 1);
        visited[i][j] = false;

        return flag;
    }
}

// 可以省略visited数组，直接修改board的值
class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existHelper(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean existHelper(char[][] board, int i, int j, String word, int start) {
        if (start >= word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (board[i][j] == word.charAt(start++)) {
            char c = board[i][j];
            board[i][j] = '#';
            boolean res = existHelper(board, i + 1, j, word, start) ||
                    existHelper(board, i - 1, j, word, start) ||
                    existHelper(board, i, j + 1, word, start) ||
                    existHelper(board, i, j - 1, word, start);
            board[i][j] = c;
            return res;
        }
        return false;
    }
}