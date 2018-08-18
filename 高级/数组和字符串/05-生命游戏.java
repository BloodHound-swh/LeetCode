/**
 * 生命游戏
根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。

示例:

输入: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
输出: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
进阶:

你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */



// dead <- dead 00
// dead <- live 01
// live <- dead 10
// live <- live 11
// 原地算法：用两位数来表示，个位表示当前状态，十位表示下一个状态。状态更新就用%10求余即可
class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = getLiveNum(board, i, j);
                if (board[i][j] == 0) {
                    if (x == 3)
                        board[i][j] += 10;
                } else {
                    if (x == 2 || x == 3)
                        board[i][j] += 10;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] /= 10;
            }
        }
    }

    private int getLiveNum(int[][] board, int x, int y) {
        int num = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || (i == x && j == y))
                    continue;
                if (board[i][j] % 10 == 1)
                    num++;
            }
        }
        return num;
    }
}

// 非原地算法就比较简单了，用两个数组即可，ans[]来储存下一个状态
class Solution {
    public void gameOfLife(int[][] board) {
        int x = board.length;
        int y = board[0].length;
        int[][] ans = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 0) {
                    if (getAliveCell(board, i, j) == 3) {
                        ans[i][j] = 1;
                    }
                } else if (board[i][j] == 1) {
                    int surround = getAliveCell(board, i, j);
                    if (surround < 2) {
                        ans[i][j] = 0;
                    } else if (surround <= 3) {
                        ans[i][j] = 1;
                    } else {
                        ans[i][j] = 0;
                    }
                }
            }
        }
        updateBoard(board, ans);
    }

    private void updateBoard(int[][] board, int[][] ans) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ans[i][j];
            }
        }
    }

    private int getAliveCell(int[][] board, int i, int j) {
        int count = 0;
        for (int k = ((i - 1) >= 0 ? i - 1 : 0); k <= ((i + 1) < board.length ? i + 1 : board.length - 1); k++) {
            for (int l = ((j - 1) >= 0 ? j - 1 : 0); l <= (j + 1 < board[k].length ? j + 1
                    : board[k].length - 1); l++) {
                if (board[k][l] == 1) {
                    count++;
                }
            }

        }
        if (board[i][j] == 1)
            count--;
        return count;
    }
}