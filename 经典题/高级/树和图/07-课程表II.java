/*
 * 课程表 II
现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

示例 1:

输入: 2, [[1,0]] 
输出: [0,1]
解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2:

输入: 4, [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。
 */

// 简单的做法是根据上一道题的Queue，每poll处一个课程就存在结果数组，看最后数组长度是否等于课程数量即可
// 我们也可以不需要Queue，直接用数组来记录，使用一个双指针
// last指针来记录可以加入到数组的课程
// first指针来模拟上课顺序
// 当first == last时，看last是否等于课程数量
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        int[] results = new int[numCourses];
        if (numCourses <= 0 || prerequisites == null)
            return results;
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : prerequisites) {
            inDegree[pair[0]]++;
            if (graph.containsKey(pair[1])) {
                graph.get(pair[1]).add(pair[0]);
            } else {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(pair[0]);
                graph.put(pair[1], newList);
            }
        }
        int first = 0, last = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                results[last++] = i;
        }
        while (first < last) {
            List<Integer> subCourses = graph.get(results[first]);
            if (subCourses != null) {
                for (int sub : subCourses) { // 模拟上课顺序，并将它的后续课程存放在results中，并将last后移
                    if (--inDegree[sub] == 0)
                        results[last++] = sub;
                }
            }
            first++;
        }
        if (last != numCourses)
            return new int[0];
        return results;
    }
}

// DFS 仅供参考，没有BFS简洁
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return new int[0];
        }

        // 课程 -> 后续可做的课程
        Map<Integer, List<Integer>> preMap = new HashMap();

        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = preMap.get(prerequisites[i][0]);
            if (list == null) {
                list = new ArrayList();
            }
            list.add(prerequisites[i][1]);
            preMap.put(prerequisites[i][0], list);
        }

        List<Integer> courseList = new ArrayList(numCourses);
        // 0没有访问过，１成功访问，－１当前正在使用
        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, preMap, visited, courseList)) {
                return new int[0];
            }
        }

        int[] res = new int[courseList.size()];

        for (int i = 0; i < courseList.size(); i++) {
            res[i] = courseList.get(i);
        }

        return res;
    }

    private boolean dfs(int k, Map<Integer, List<Integer>> preMap, int[] visited, List<Integer> courseList) {
        if (visited[k] == 1) {
            return true;
        } else if (visited[k] == -1) {
            return false;
        }

        visited[k] = -1;

        List<Integer> list = preMap.get(k);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (!dfs(list.get(i), preMap, visited, courseList)) {
                    return false;
                }
            }
        }

        visited[k] = 1;
        courseList.add(k);

        return true;
    }
}