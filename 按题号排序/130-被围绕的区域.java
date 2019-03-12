/**
 * 被围绕的区域

给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:

X X X X
X O O X
X X O X
X O X X
运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X
解释:

被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */


// DFS
// 在网上看到大家普遍的做法是扫面矩阵的四条边，如果有O，则用DFS遍历，将所有连着的O都变成另一个字符，比如B
// 这样剩下的O都是被包围的，然后将这些O变成X，把B变回O就行了
class Solution {
    private int[] dx = { 1, -1, 0, 0 };
    private int[] dy = { 0, 0, 1, -1 };

    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;

        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    fill(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'B')
                    board[i][j] = 'O';
            }
        }
    }

    public void fill(char[][] board, int x, int y) {
        int row = board.length;
        int col = board[0].length;
        if (x < 0 || y < 0 || x >= row || y >= col || board[x][y] != 'O') {
            return;
        }

        board[x][y] = 'B';
        for (int i = 0; i < dx.length; i++) {
            fill(board, x + dx[i], y + dy[i]);
        }
    }
}