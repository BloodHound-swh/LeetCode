/**
 * 克隆图
给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。

示例：



输入：
{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

解释：
节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 

提示：

节点数介于 1 到 100 之间。
无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
必须将给定节点的拷贝作为对克隆图的引用返回。
 */


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

// 使用深度优先搜索DFS来解答此题
// 在递归函数中，首先判空，然后再看当前的结点是否已经被克隆过了，若在HashMap中存在，则直接返回其映射结点。
// 否则就克隆当前结点，并在HashMap中建立映射，然后遍历当前结点的所有neihbor结点，调用递归函数并且加到克隆结点的neighbors数组中即可
class Solution {
    public HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        return helper(node);
    }

    public Node helper(Node node) {
        if (node == null)
            return null;
        if (map.containsKey(node))
            return map.get(node);

        Node copyNode = new Node(node.val, new ArrayList<Node>());
        map.put(node, copyNode);
        for (Node neighbor : node.neighbors) {
            Node clone = helper(neighbor);
            copyNode.neighbors.add(clone);
        }

        return copyNode;
    }
}