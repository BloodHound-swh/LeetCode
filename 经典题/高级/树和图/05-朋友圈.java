/*
 * Friend Circles
班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。

示例 1:

输入: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
输出: 2 
说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
第2个学生自己在一个朋友圈。所以返回2。
示例 2:

输入: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
输出: 1
说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
注意：

N 在[1,200]的范围内。
对于所有学生，有M[i][i] = 1。
如果有M[i][j] = 1，则有M[j][i] = 1。
 */

// 使用深度优先遍历，找到每一个人的所有朋友，然后将关系都置零，再以下一个人为起点找朋友圈
class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        if (n == 0)
            return 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (M[i][i] == 0)
                continue;
            ans++;
            dfs(M, i, n);
        }
        return ans;
    }

    public void dfs(int[][] M, int curr, int n) {
        for (int i = 0; i < n; i++) {
            if (M[curr][i] == 0)
                continue;
            M[curr][i] = M[i][curr] = 0; // 此时也把M[curr][curr] = 0 了
            dfs(M, i, n);
        }
    }
}

// 使用visited数组效率会更高，因为二维数组内存可能不连续
class Solution {
    public int findCircleNum(int[][] M) {
        boolean[] isVisited = new boolean[M.length];
        int count = 0;
        for (int i = 0; i < M.length; ++i) {
            if (!isVisited[i]) {
                count++;
                dfs(i, M, isVisited);
            }

        }
        return count;
    }

    private void dfs(int i, int[][] m, boolean[] isVisited) {
        for (int j = 0; j < m[i].length; ++j) {
            if (!isVisited[j] && m[i][j] == 1) {
                isVisited[j] = true;
                dfs(j, m, isVisited);
            }
        }
    }
}

// BFS
class Solution {
    public int findCircleNum(int[][] M) {
        if (M.length == 0)
            return 0;
        int nums = M.length, cir = 0;
        int[] visited = new int[nums];
        for (int i = 0; i < nums; i++) {
            Queue<Integer> queue = new LinkedList<>();
            if (visited[i] == 0) {
                queue.add(i);
                visited[i] = 1;
                cir++;
                while (!queue.isEmpty()) {
                    int top = queue.poll();
                    for (int j = 0; j < nums; j++) {
                        if (visited[j] == 0 && M[top][j] == 1) {
                            queue.add(j);
                            visited[j] = 1;
                        }
                    }
                }
            }
        }
        return cir;
    }
}