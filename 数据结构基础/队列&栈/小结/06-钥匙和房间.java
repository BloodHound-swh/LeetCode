/**
 * 钥匙和房间
有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。

在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。

最初，除 0 号房间外的其余所有房间都被锁住。

你可以自由地在房间之间来回走动。

如果能进入每个房间返回 true，否则返回 false。

示例 1：

输入: [[1],[2],[3],[]]
输出: true
解释:  
我们从 0 号房间开始，拿到钥匙 1。
之后我们去 1 号房间，拿到钥匙 2。
然后我们去 2 号房间，拿到钥匙 3。
最后我们去了 3 号房间。
由于我们能够进入每个房间，我们返回 true。
示例 2：

输入：[[1,3],[3,0,1],[2],[0]]
输出：false
解释：我们不能进入 2 号房间。
提示：

1 <= rooms.length <= 1000
0 <= rooms[i].length <= 1000
所有房间中的钥匙数量总计不超过 3000。
 */


// DFS
// 使用visited记录已经去过的房间，每次进去一个房间，就用dfs遍历里面的钥匙
// 最后看visited里元素的个数是否等于房间的总个数
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        HashSet<Integer> visited = new HashSet<>();
        dfs(rooms, 0, visited);
        return visited.size() == rooms.size();
    }

    public void dfs(List<List<Integer>> rooms, int curr, HashSet<Integer> visited) {
        if (visited.contains(curr))
            return;
        visited.add(curr);
        for (int next : rooms.get(curr)) {
            dfs(rooms, next, visited);
        }
    }
}

// 优化DFS
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, visited, 0);
        for (boolean v : visited) {
            if (!v)
                return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, boolean[] visited, int i) {
        if (visited[i])
            return;
        visited[i] = true;
        for (int j : rooms.get(i)) {
            if (i == j)
                continue;
            dfs(rooms, visited, j);
        }
    }
}

// BFS
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0)
            return false;
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i : rooms.get(0)) {
            q.offer(i);
        }

        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;

        while (!q.isEmpty()) {
            int key = q.poll();
            if (visited[key])
                continue;
            visited[key] = true;
            for (int i : rooms.get(key)) {
                q.offer(i);
            }
        }

        for (boolean v : visited) {
            if (!v)
                return false;
        }

        return true;
    }
}