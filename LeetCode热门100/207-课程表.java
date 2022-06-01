/**
 * 207. 课程表
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

 

示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
示例 2：

输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 

提示：

1 <= numCourses <= 10^5
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
prerequisites[i] 中的所有课程对 互不相同
 */


// 使用入度作为切入点，使用广度优先遍历。
class Solution {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        // map的value表示，以当前课程作为前提的课程列表
        for (int i = 0; i < prerequisites.length; i++) {
            if (map.containsKey(prerequisites[i][1])) {
                map.get(prerequisites[i][1]).add(prerequisites[i][0]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(prerequisites[i][0]);
                map.put(prerequisites[i][1], list);
            }
            // 对应的后置课程入度加1
            indegree[prerequisites[i][0]]++;
        }

        // 入度为0的课程放入队列，即可以直接学习的课程
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int learned = 0;
        while (!q.isEmpty()) {
            learned++;
            int lesson = q.poll();
            if (map.get(lesson) != null) {
                // 遍历当前课程的后置课程
                for (Integer nextLesson : map.get(lesson)) {
                    // 入度减一，表示前置课程已经被学习了一个，让入度为0的时候放入可直接学习的队列
                    indegree[nextLesson]--;
                    if (indegree[nextLesson] == 0) {
                        q.offer(nextLesson);
                    }
                }
            }
        }

        return learned == numCourses;
    }
}

// 以出度为切入点，使用DFS，并且将HashMap改为ArrayList，速度更快
// ArrayList第i位存放的是i课程的先决课程precourse
// dfs用1和2来标记状态，先将课程标记为1表示等待学习，若是在dfs过程中碰到了其它的1，说明出现了环
// 若是在dfs过程中没有出现1，则从后往前以此标记为2表示已经学习过了，并且此时在这个课程路径上没有环出现
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

        visited[curr] = 1; // 表示等待学习
        for (int pre : graph.get(curr)) {
            if (dfs(pre, graph, visited))
                return true; // 说明出现了环
        }

        visited[curr] = 2; // 表示已经学习过了
        return false;
    }
}