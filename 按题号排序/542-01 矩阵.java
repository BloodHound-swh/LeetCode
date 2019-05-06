/**
 * 01 矩阵
给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。

两个相邻元素间的距离为 1 。

示例 1: 
输入:

0 0 0
0 1 0
0 0 0
输出:

0 0 0
0 1 0
0 0 0
示例 2: 
输入:

0 0 0
0 1 0
1 1 1
输出:

0 0 0
0 1 0
1 2 1
注意:

给定矩阵的元素个数不超过 10000。
给定矩阵中至少有一个元素是 0。
矩阵中的元素只在四个方向上相邻: 上、下、左、右。

 */


// 我们可以首先遍历一次矩阵，将值为0的点都存入queue，将值为1的点改为INT_MAX。
// 之前像什么遍历迷宫啊，起点只有一个，而这道题所有为0的点都是起点，这想法，叼！
// 然后开始BFS遍历，从queue中取出一个数字，遍历其周围四个点，如果越界或者周围点的值小于等于当前值，则直接跳过。
// 因为周围点的距离更小的话，就没有更新的必要，否则将周围点的值更新为当前值加1，然后把周围点的坐标加入queue，
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // 比BFS还要简单一点
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];
                if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length
                        || matrix[x][y] <= matrix[cur[0]][cur[1]]) {
                    continue;
                }
                matrix[x][y] = matrix[cur[0]][cur[1]] + 1;
                queue.offer(new int[] { x, y });
            }
        }

        return matrix;
    }
}