/**
 * 有效的数独

判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。


上图是一个部分填充的有效的数独。

数独部分空格内已填入了数字，空白格用 '.' 表示。

示例 1:

输入:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
输出: true
示例 2:

输入:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
输出: false
解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
说明:

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
给定数独序列只包含数字 1-9 和字符 '.' 。
给定数独永远是 9x9 形式的。
 */


// 未看答案版，并没有完全做出来，因为第三部分的subMatrix检查的坐标变换还是查看了答案
// 另外，这题用visited[]数组会更快
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                if (set.contains(board[i][j]))
                    return false;
                else
                    set.add(board[i][j]);
            }
        }

        for (int j = 0; j < 9; j++) {
            HashSet<Character> set = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                if (board[i][j] == '.')
                    continue;
                if (set.contains(board[i][j]))
                    return false;
                else
                    set.add(board[i][j]);
            }
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                HashSet<Character> set = new HashSet<>();
                for (int k = 0; k < 9; k++) {
                    if (board[i + k / 3][j + k % 3] == '.')
                        continue;
                    if (set.contains(board[i + k / 3][j + k % 3]))
                        return false;
                    else
                        set.add(board[i + k / 3][j + k % 3]);
                }
            }
        }
        return true;
    }
}

// 答案一
// 将set换成数组
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            boolean[] visited = new boolean[9];
            for (int col = 0; col < 9; col++) {
                char c = board[row][col];
                if (c != '.') {
                    int num = c - '1';
                    if (visited[num] == true)
                        return false;
                    visited[num] = true;
                }
            }
        }
        
        for (int col = 0; col < 9; col++) {
            boolean[] visited = new boolean[9];
            for (int row = 0; row < 9; row++) {
                char c = board[row][col];
                if (c != '.') {
                    int num = c - '1';
                    if (visited[num] == true)
                        return false;
                    visited[num] = true;
                }
            }
        }
        
        for (int box = 0; box < 9; box++) {
            boolean[] visited = new boolean[9];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    char c = board[row + 3 * (box / 3)][col + 3 * (box % 3)];
                    if (c != '.') {
                        int num = c - '1';
                        if (visited[num] == true) 
                            return false;
                        visited[num] = true;
                    }
                }
            }
        }
        
        return true;
    }
}

// 答案二
// 对循环进行优化
class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0 || board.length != board[0].length) {
            return false;
        }
        HashSet<Character> row, col, block;
        int n = board.length;

        for (int i = 0; i < n; i++) {
            row = new HashSet<Character>();
            col = new HashSet<Character>();
            block = new HashSet<Character>();
            for (int j = 0; j < n; j++) {
                // Check row
                if (!row.contains(board[i][j])) {
                    row.add(board[i][j]);
                } else if (board[i][j] != '.') {
                    return false;
                }
                // Check col
                if (!col.contains(board[j][i])) {
                    col.add(board[j][i]);
                } else if (board[j][i] != '.') {
                    return false;
                }
                // check block
                int c = j % 3 + 3 * (i % 3);// make use of how i and j increases
                int r = j / 3 + 3 * (i / 3);
                if (!block.contains(board[r][c])) {
                    block.add(board[r][c]);
                } else if (board[r][c] != '.') {
                    return false;
                }
            }
        }

        return true;
    }
}


// 如果实在不会subMatrix的坐标变换，可以看看这个笨方法
class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0 || board.length != board[0].length) {
            return false;
        }
        HashSet<Character> row, col, block;
        int n = board.length;

        for (int i = 0; i < n; i++) {
            row = new HashSet<Character>();
            col = new HashSet<Character>();
            for (int j = 0; j < n; j++) {
                // Check row
                if (!row.contains(board[i][j])) {
                    row.add(board[i][j]);
                } else if (board[i][j] != '.') {
                    return false;
                }
                // Check col
                if (!col.contains(board[j][i])) {
                    col.add(board[j][i]);
                } else if (board[j][i] != '.') {
                    return false;
                }
            }
        }

        for (int i = 0; i < n; i += 3) {
            for (int j = 0; j < n; j += 3) {
                block = new HashSet<Character>();
                // Check block
                for (int k = 0; k < 3; k++) {
                    for (int h = 0; h < 3; h++) {
                        if (!block.contains(board[i + k][j + h])) {
                            block.add(board[i + k][j + h]);
                        } else if (board[i + k][j + h] != '.') {
                            return false;
                        }
                    }
                }
            }

        }

        return true;
    }
}

// 使用一个bollean数组来记录数字是否重复出现，并编辑process函数将char转为数字，进而转为坐标来控制bollean数组的值，并以此进行判断。
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[9];

        // row
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!process(visited, board[i][j]))
                    return false;
            }
        }

        // col
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!process(visited, board[j][i]))
                    return false;
            }
        }

        // sub matrix
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(visited, false);
                for (int k = 0; k < 9; k++) {
                    if (!process(visited, board[i + k / 3][j + k % 3]))
                        return false;
                }
            }
        }
        return true;

    }

    private boolean process(boolean[] visited, char digit) {
        if (digit == '.') {
            return true;
        }

        int num = digit - '0';
        if (num < 1 || num > 9 || visited[num - 1]) {
            return false;
        }

        visited[num - 1] = true;
        return true;
    }
}