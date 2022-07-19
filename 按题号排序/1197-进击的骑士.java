/**
 *  进击的骑士
一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上，你的 骑士 驻扎在坐标为 [0, 0] 的方格里。

骑士的走法和中国象棋中的马相似，走 “日” 字：即先向左（或右）走 1 格，再向上（或下）走 2 格；或先向左（或右）走 2 格，再向上（或下）走 1 格。

每次移动，他都可以按图示八个方向之一前进。



返回 骑士前去征服坐标为 [x, y] 的部落所需的最小移动次数 。本题确保答案是一定存在的。

 

示例 1：

输入：x = 2, y = 1
输出：1
解释：[0, 0] → [2, 1]
示例 2：

输入：x = 5, y = 5
输出：4
解释：[0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 

提示：

-300 <= x, y <= 300
0 <= |x| + |y| <= 300
 */


// 1、添加到队列的点必须是步步逼近（x，y）的，所以距离需要不断减少（贪心） 
// 2、在第1点的基础上，需要允许一些点适当远离（x,y）,以便能搜索到答案（剪枝）
class Solution {
    int[][] dirs = new int[][]{{2, -1}, {2, 1}, {1, -2}, {1, 2},
        {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}};

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, getDistance(0, 0,x, y)));
        Set<String> visited = new HashSet<>();
        visited.add(0 + " " + 0);

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Node node = queue.poll();
                if (node.x == x && node.y == y) {
                    return count;
                }

                for (int[] dir : dirs) {
                    int newX = node.x + dir[0];
                    int newY = node.y + dir[1];
                    int path = node.path;
                    int newPath = getDistance(newX,newY,x,y);
                    if (visited.contains(newX + " " + newY)) {
                        continue;
                    }
                    // 注意这里的4，表示不能完全反方向走动
                    if (newPath > path && newPath > 4) {
                        continue;
                    }
                    queue.add(new Node(newX, newY, newPath));
                    visited.add(newX + " " + newY);
                }
            }
            count++;
        }
        return count;
    }

    // 计算两点之间的距离
    private int getDistance(int srcX, int srcY, int destX, int destY) {
        return Math.abs(destX - srcX) +  Math.abs(destY - srcY);
    }

    class Node{
        public int x;
        public int y;
        public int path;

        public Node(int x, int y, int path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }
    }
}

// 这是错误方法，没有考虑到棋子走动的方向可能会远离目标值，造成结果出错
class Solution {
    int[][] move = new int[][]{{2, -1}, {2, 1}, {1, -2}, {1, 2},
        {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}};

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, 0});
        int step = 0;
        int[][] visited = new int[1000][1000];
        while (!stack.isEmpty()) {
            for (int i = 0; i < stack.size(); i++) {
                int[] from = stack.pop();
                if (visited[from[0] + 300][from[1] + 300] == 1) {
                    continue;
                }
                for (int[] m : move) {
                    int row = from[0] + m[0];
                    int col = from[1] + m[1];
                    visited[row + 300][col + 300] = 1;
                    if (row == x && col == y) {
                        return step;
                    }
                    stack.push(new int[]{row, col});
                }
            }
            step++;
        }

        return step;
    }
}