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

// 这道题的解法要用递归和DP来解，用DP的原因是为了提高效率，避免重复运算。
// 我们需要维护一个二维动态数组dp，其中dp[i][j]表示数组中以(i,j)为起点的最长递增路径的长度，初始将dp数组都赋为0
// 当我们用递归调用时，遇到某个位置(x, y), 如果visited[x][y]为true的话，我们直接返回dp[x][y]即可，不需要重复计算。
// 我们需要以数组中每个位置都为起点调用递归来做，比较找出最大值。
// 在以一个位置为起点用DFS搜索时，对其四个相邻位置进行判断，如果相邻位置的值大于上一个位置，则对相邻位置继续调用递归，并更新一个最大值，搜素完成后返回即可
class Solution {
    boolean[][] visited;
    int[][] dp;
    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };

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
        dp[x][y] = 1;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length && matrix[x][y] < matrix[nx][ny])
                dp[x][y] = Math.max(dp[x][y], dfs(matrix, nx, ny) + 1);
        }
        visited[x][y] = true;
        return dp[x][y];
    }
}

// 方法一，“美化”一下
class Solution {
    private int[] row = { -1, 1, 0, 0 };
    private int[] col = { 0, 0, -1, 1 };

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int[][] len = new int[matrix.length][matrix[0].length];
        int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, find(matrix, visited, len, i, j));
            }
        }
        return max;
    }

    private int find(int[][] matrix, boolean[][] visited, int[][] len, int x, int y) {
        if (visited[x][y])
            return len[x][y];
        len[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int curX = x + row[i];
            int curY = y + col[i];
            if (curX >= 0 && curX < matrix.length && curY >= 0 && curY < matrix[0].length
                    && matrix[curX][curY] < matrix[x][y]) {
                len[x][y] = Math.max(len[x][y], find(matrix, visited, len, curX, curY) + 1);
            }
        }
        visited[x][y] = true;
        return len[x][y];
    }
}


// 发现了一个BFS的解法，C++版本
// 需要用queue来辅助遍历，我们还是需要dp数组来减少重复运算。
// 遍历数组中的每个数字，跟上面的解法一样，把每个遍历到的点都当作BFS遍历的起始点，
// 需要优化的是，如果当前点的dp值大于0了，说明当前点已经计算过了，我们直接跳过。
// 否则就新建一个queue，然后把当前点的坐标加进去，再用一个变量cnt，初始化为1，表示当前点为起点的递增长度，然后进入while循环，然后cnt自增1，
// 这里先自增1没有关系，因为只有当周围有合法的点时候才会用cnt来更新。
// 由于当前结点周围四个相邻点距当前点距离都一样，所以采用类似二叉树层序遍历的方式，先出当前queue的长度，
// 然后遍历跟长度相同的次数，取出queue中的首元素，对周围四个点进行遍历，计算出相邻点的坐标后，要进行合法性检查，横纵坐标不能越界，且相邻点的值要大于当前点的值，并且相邻点点dp值要小于cnt，才有更新的必要。
// 用cnt来更新dp[x][y]，并用cnt来更新结果res，然后把相邻点排入queue中继续循环即可，

// // class Solution {
//     public:
//         int longestIncreasingPath(vector<vector<int>>& matrix) {
//             if (matrix.empty() || matrix[0].empty()) return 0;
//             int m = matrix.size(), n = matrix[0].size(), res = 1;
//             vector<vector<int>> dirs{{0,-1},{-1,0},{0,1},{1,0}};
//             vector<vector<int>> dp(m, vector<int>(n, 0));
//             for (int i = 0; i < m; ++i) {
//                 for (int j = 0; j < n; ++j ) {
//                     if (dp[i][j] > 0) continue;
//                     queue<pair<int, int>> q{{{i, j}}};
//                     int cnt = 1;
//                     while (!q.empty()) {
//                         ++cnt;
//                         int len = q.size();
//                         for (int k = 0; k < len; ++k) {
//                             auto t = q.front(); q.pop();
//                             for (auto dir : dirs) {
//                                 int x = t.first + dir[0], y = t.second + dir[1];
//                                 if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[t.first][t.second] || cnt <= dp[x][y]) 
//                                      continue;
//                                 dp[x][y] = cnt;
//                                 res = max(res, cnt);
//                                 q.push({x, y});
//                             }
//                         }
//                     }
//                 }
//             }
//             return res;
//         }
//     };
