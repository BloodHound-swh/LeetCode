/**
 * 332. 重新安排行程
给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。

所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。

例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。

 

示例 1：


输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
输出：["JFK","MUC","LHR","SFO","SJC"]
示例 2：


输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 

提示：

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi 和 toi 由大写英文字母组成
fromi != toi
 */

// DFS深度优先遍历
// https://www.youtube.com/watch?v=kZXsB3WemYY
// https://www.youtube.com/watch?v=VWVv1zf30gE
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        // 邻接表，key为起点，value为能去的终点，并且按字母排序
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        buildMap(map, tickets);
        Deque<String> dq = new LinkedList<>();
        dfs(dq, map, "JFK");
        while (!dq.isEmpty()) {
            res.add(dq.pollLast());
        }

        return res;
    }

    // 建立联结表
    public void buildMap(Map<String, PriorityQueue<String>> map, List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            // PriorityQueue<String> pq = map.getOrDefault(from, new PriorityQueue<String>());
            // pq.offer(to);
            // map.put(from, pq);
            map.putIfAbsent(from, new PriorityQueue<String>());
            map.get(from).offer(to);
        }
    }

    // 深度优先遍历
    public void dfs(Deque<String> dq, Map<String, PriorityQueue<String>> map, String from) {
        PriorityQueue<String> pq = map.get(from);
        // 因为存在某个地点从来没有作为起点过，所以建立联结表时没有这个key，那么map.get就会为null了
        while (pq != null && !pq.isEmpty()) {
            String to = pq.poll();
            dfs(dq, map, to);
        }
        // 注意这里队列里的顺序相对于答案的顺序是反的
        dq.offer(from);
    }


}