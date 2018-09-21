/*
有效的数独
判断一个数独是否有效，根据：Sudoku Puzzles - The Rules。
数独部分填了数字，空的部分用 '.' 表示。
一个部分填充是有效的数独。
说明:
一个有效的数独（填了一部分的）不一定是可解的，只要已经填的数字是有效的即可。
 */

//在nest for loop里面validate row,col,and block.     
// validate block要利用i 和 j 增长的规律。    
// 说白了，i && j是按照0~n增长的index, 具体怎么用是可以flexible的。这个方法在同一个nest for loop解决所有运算。
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

// 单独做block validation: validate block的时候虽然看到了4层for.其实也就是n^2
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