/*
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

// 从矩阵的四条边上的点作为起点，搜索连续的一块区域上值为O的点并赋为一个临时变量。
// 对四条边上所有点都进行过这个步骤后，矩阵内剩余的O就是被包围的O。此时再对所有点遍历一遍，将临时变量赋回O，而剩余的O则赋为X。
// 用队列实现BFS时，赋临时变量B时要在将周边点加入队列时就赋值，减少while循环的次数，否则Leetcode会超时
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) && board[i][j] == 'O') {
                    Queue<Integer> q = new LinkedList<>();
                    q.offer(i * board[0].length + j);
                    board[i][j] = 'B';
                    while (!q.isEmpty()) {
                        Integer key = q.poll();
                        int x = key / board[0].length;
                        int y = key % board[0].length;
                        if (x > 0 && board[x - 1][y] == 'O') {
                            q.offer((x - 1) * board[0].length + y);
                            board[x - 1][y] = 'B';
                        }
                        if (x < board.length - 1 && board[x + 1][y] == 'O') {
                            q.offer((x + 1) * board[0].length + y);
                            board[x + 1][y] = 'B';
                        }
                        if (y > 0 && board[x][y - 1] == 'O') {
                            q.offer(x * board[0].length + y - 1);
                            board[x][y - 1] = 'B';
                        }
                        if (y < board[0].length - 1 && board[x][y + 1] == 'O') {
                            q.offer(x * board[0].length + y + 1);
                            board[x][y + 1] = 'B';
                        }
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'B')
                    board[i][j] = 'O';
            }
        }
    }
}

// DFS
class Solution {
    private int[] dx = { 1, -1, 0, 0 };
    private int[] dy = { 0, 0, 1, -1 };

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        // Check the board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    fill(board, i, j);
                }
            }
        }
        char mark = 'M', candidate = 'O', goal = 'X';
        // Replacement
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == candidate) {
                    board[i][j] = goal;
                }
                if (board[i][j] == mark) {
                    board[i][j] = candidate;
                }
            }
        }
    }

    // DFS to traverse all border nodes and their connected nodes
    public void fill(char[][] board, int x, int y) {
        int row = board.length, col = board[0].length;
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'M';
        for (int i = 0; i < dx.length; i++) {
            fill(board, x + dx[i], y + dy[i]);
        }
    }
}