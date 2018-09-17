/**
 * 单词搜索
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.
 */



 
// 类似于岛屿个数那题，使用boolean数组记录字母是否被用过，然后从当前字母的上下左右进行搜索
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null)
            return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (existHelper(board, used, word.toCharArray(), 0, col, row)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existHelper(char[][] board, boolean[][] used, char[] word, int idx, int col, int row) {
        if (idx == word.length)
            return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return false;
        if (used[row][col] == true || board[row][col] != word[idx])
            return false;

        used[row][col] = true;
        boolean exist = existHelper(board, used, word, idx + 1, col + 1, row);
        if (exist)
            return true;

        exist = existHelper(board, used, word, idx + 1, col - 1, row);
        if (exist)
            return true;

        exist = existHelper(board, used, word, idx + 1, col, row + 1);
        if (exist)
            return true;

        exist = existHelper(board, used, word, idx + 1, col, row - 1);
        if (exist)
            return true;

        used[row][col] = false;
        return false;

    }
}


// 对上的的方法的代码进行简化，但是由于在方向上没有if的判断，所以运行时间会长一些
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
        if (start >= word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;
        if (board[i][j] == word.charAt(start++)) {
            char c = board[i][j];
            board[i][j] = '#';
            boolean res = existHelper(board, i + 1, j, word, start) || existHelper(board, i - 1, j, word, start)
                    || existHelper(board, i, j + 1, word, start) || existHelper(board, i, j - 1, word, start);
            board[i][j] = c;
            return res;
        }
        return false;
    }
}