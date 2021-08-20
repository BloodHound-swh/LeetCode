/**
 * 剑指 Offer 47. 礼物的最大价值
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

 

示例 1:

输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 

提示：

0 < grid.length <= 200
0 < grid[0].length <= 200
 */

// 动态规划
// 设d(i, j)为从棋盘左上角走至单元格(i, j)的礼物最大累计价值，易得到以下递推关系：
// d(i, j)等于d(i, j-1)和d(i-1,j)中的较大值加上当前单元格礼物价值grid(i, j)。
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int s = 1; s < n; s++) {
            grid[0][s] += grid[0][s - 1];
        }
        for (int s = 1; s < m; s++) {
            grid[s][0] += grid[s - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[m - 1][n - 1];
    }
}

