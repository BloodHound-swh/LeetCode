/**
 * 378. 有序矩阵中第K小的元素

给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
请注意，它是排序后的第k小元素，而不是第k个元素。

示例:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

返回 13。
说明: 
你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 */

// 行上边上不到突破，就看看能不能先从列入手

// 答案一
// 使用优先队列，先将矩阵的第一列放入队列中自动排序
// 每次poll出最小的值，并将它右边的值也加入到队列中，然后k--
// 当k == 1 时，就是要找的数
class Solution {
    class Node {
        int x, y, val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || k <= 0) {
            return 0;
        }

        int n = matrix.length;

        Queue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });

        for (int i = 0; i < n; i++) {
            queue.offer(new Node(i, 0, matrix[i][0]));
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (k == 1)
                return node.val;
            if (node.y + 1 < n)
                queue.offer(new Node(node.x, node.y + 1, matrix[node.x][node.y + 1]));
            k--;
        }
        return 0;
    }
}

// 答案二
// 使用二分法，mid初始值为左上角有右下角的均值，使用辅助函数找矩阵中有cnt个数比mid小
// 当cnt < k 时，lo = mid + 1，否则hi = mid
// 最后返回lo即可
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || k <= 0) {
            return 0;
        }
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = countLessEqual(matrix, mid);

            if (cnt < k)
                lo = mid;
            else
                hi = mid;
        }

        if (countLessEqual(matrix, lo) >= k) {
            return lo;
        } else {
            return hi;
        }
    }

    private int countLessEqual(int[][] matrix, int target) {
        int len = matrix.length;
        int i = len - 1, j = 0, cnt = 0; // 从左下角开始找
        while (i >= 0 && j < len) {
            if (matrix[i][j] <= target) {
                cnt += (i + 1); // 代表这一列从第0到第i个元素位置都小于等于target
                j++;
            } else
                i--;
        }
        return cnt;
    }
}