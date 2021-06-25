/**
 * 剑指 Offer 13. 机器人的运动范围
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 2：

输入：m = 3, n = 1, k = 0
输出：1
提示：

1 <= n,m <= 100
0 <= k <= 20
 */

// 广度优先遍历，且可以简化为只向左和向右运动，一般广度优先都会使用到队列
class Solution {
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }

        boolean[][] visited = new boolean[m][n];
        int res = 1;
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] step = queue.poll();
            for (int i = 0; i < 2; i++) {
                int x = step[0] + dx[i];
                int y = step[1] + dy[i];
                int sum = calc(x) + calc(y);
                if (x > (m - 1) || y > (n - 1) || sum > k || visited[x][y] == true) {
                    continue;
                } 
                res++;
                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }

        return res;
    }

    public int calc(int x) {
        int res = 0;
        while (x > 0) {
            res += x % 10;
            x = x / 10;
        }

        return res;
    }
}

// dfs
class Solution {
    int res = 0;
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        dfs(0, 0, m, n, k, visited);
        return res;
    }

    public void dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i >= m || j >= n || calc(i) + calc(j) > k || visited[i][j] == true) {
            return;
        }
        res++;
        visited[i][j] = true;
        dfs(i + 1, j, m, n, k, visited);
        dfs(i, j + 1, m, n, k, visited);
    }

    public int calc(int x) {
        int res = 0;
        while (x > 0) {
            res += x % 10;
            x = x / 10;
        }

        return res;
    }
}