/**
 * 课程表
现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？

示例 1:

输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。
 */


// 使用HashMap来存放课程，以及它的后续课程列表
// 使用一个数组来记录每一个课程的“入度”，即先决课程的个数
// 使用BFS来遍历入度为零的课程，同时将它的后续课程列表中的课程入度减一，若发现某一课程入度为零了，则加入道队列之中
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0)
            return true;
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            if (graph.containsKey(prerequisites[i][1])) {
                graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            } else {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(prerequisites[i][0]);
                graph.put(prerequisites[i][1], list);
            }
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            List<Integer> subcourses = graph.get(course);
            for (int i = 0; subcourses != null && i < subcourses.size(); i++) {
                if (--inDegree[subcourses.get(i)] == 0)
                    queue.offer(subcourses.get(i));
            }
        }
        // 若此时还有节点的入度不为0，则说明环存在，返回false
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] != 0)
                return false;
        }
        return true;
    }
}

// 使用DFS，并且将HashMap改为ArrayList，速度更快
// ArrayList第i位存放的是i课程的先决课程precourse
// dfs用1和2来标记状态，先将课程标记为1，若是在dfs过程中碰到了其它的1，说明出现了环
// 若是在dfs过程中没有出现1，则从后往前以此标记为2，表示没有出现环
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int precourse = prerequisites[i][1];
            graph.get(course).add(precourse);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, visited))
                return false;
        }
        return true;
    }

    public boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited) {
        if (visited[curr] == 1)
            return true; // 说明出现了环
        if (visited[curr] == 2)
            return false;
        visited[curr] = 1;
        for (int next : graph.get(curr)) {
            if (dfs(next, graph, visited)) // 遍历它的邻居只要有任意一个true就说明有环
                return true; // 说明出现了环
        }
        visited[curr] = 2;
        return false;
    }
}