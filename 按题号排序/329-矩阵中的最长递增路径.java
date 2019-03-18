/**
 * 矩阵中的最长递增路径

给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

示例 1:

输入: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
输出: 4 
解释: 最长递增路径为 [1, 2, 6, 9]。
示例 2:

输入: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
输出: 4 
解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 */

// 如果简单的使用DFS会有大量的重复计算，因此需要使用visited[][]和dp[][]数组来记录那些点已经走过，并且以改点为起始点的最长路径是多少




// 答案
// 这道题的解法要用递归和DP来解，用DP的原因是为了提高效率，避免重复运算。
// 我们需要维护一个二维动态数组dp，其中dp[i][j]表示数组中以(i,j)为起点的最长递增路径的长度，初始将dp数组都赋为0
// 当我们用递归调用时，遇到某个位置(x, y), 如果visited[x][y]为true的话，我们直接返回dp[x][y]即可，不需要重复计算。
// 我们需要以数组中每个位置都为起点调用递归来做，比较找出最大值。
// 在以一个位置为起点用DFS搜索时，对其四个相邻位置进行判断，如果相邻位置的值大于上一个位置，则对相邻位置继续调用递归，并更新一个最大值，搜素完成后返回即可
class Solution {
    boolean[][] visited;
    int[][] dp;
    int[] dx = { 1, -1, 0, 0 };
    int[] dy = { 0, 0, 1, -1 };

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        visited = new boolean[m][n];
        dp = new int[m][n];

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j));
            }
        }

        return max;
    }

    public int dfs(int[][] matrix, int x, int y) {
        if (visited[x][y])
            return dp[x][y];
        int m = matrix.length;
        int n = matrix[0].length;
        dp[x][y] = 1;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < m && ny < n && matrix[x][y] < matrix[nx][ny])
                dp[x][y] = Math.max(dp[x][y], dfs(matrix, nx, ny) + 1);
        }

        visited[x][y] = true;
        return dp[x][y];

    }
}