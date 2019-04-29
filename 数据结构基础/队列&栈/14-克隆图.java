/**
 * 克隆图
克隆一张无向图，图中的每个节点包含一个 label （标签）和一个 neighbors （邻接点）列表 。

OJ的无向图序列化：

节点被唯一标记。

我们用 # 作为每个节点的分隔符，用 , 作为节点标签和邻接点的分隔符。

例如，序列化无向图 {0,1,2#1,2#2,2}。

该图总共有三个节点, 被两个分隔符  # 分为三部分。 

第一个节点的标签为 0，存在从节点 0 到节点 1 和节点 2 的两条边。
第二个节点的标签为 1，存在从节点 1 到节点 2 的一条边。
第三个节点的标签为 2，存在从节点 2 到节点 2 (本身) 的一条边，从而形成自环。
我们将图形可视化如下：

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 */


// 这道题目的难点在于如何处理每个节点的neighbors，由于在深度拷贝每一个节点后，还要将其所有neighbors放到一个list中，而如何避免重复拷贝呢？
// 这道题好就好在所有节点值不同，所以我们可以使用HashMap来对应节点值和新生成的节点。
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        queue.offer(node);
        map.put(node, null);
        // 先用DFS拷贝所有的点
        while (queue.size() > 0) {
            UndirectedGraphNode curr = queue.poll();
            UndirectedGraphNode copy = new UndirectedGraphNode(curr.label);
            map.put(curr, copy);
            for (UndirectedGraphNode next : curr.neighbors) {
                if (!map.containsKey(next)) {
                    queue.offer(next);
                    map.put(next, null);
                }
            }
        }

        // 再拷贝所有的neighbors
        for (UndirectedGraphNode ori : map.keySet()) {
            UndirectedGraphNode cpy = map.get(ori);
            for (UndirectedGraphNode neighbor : ori.neighbors) {
                cpy.neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }
}

// 使用DFS模板
public class Solution {
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return helper(node);
    }

    public UndirectedGraphNode helper(UndirectedGraphNode node) {
        if (node == null)
            return null;
        if (map.containsKey(node))
            return map.get(node);
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            UndirectedGraphNode clone = helper(neighbor);
            copy.neighbors.add(clone);
        }

        return copy;
    }
}


// 题目改版后的解法
class Solution {
    public HashMap<Integer, Node> map = new HashMap<>();
    
    public Node cloneGraph(Node node) {
        return clone(node);
    }
    
    public Node clone(Node node) {
        if (node == null) return null;
        
        if (map.containsKey(node.val)) 
            return map.get(node.val);
        
        Node newNode = new Node(node.val, new ArrayList<Node>());
        map.put(newNode.val, newNode);
        for (Node neighbor : node.neighbors) 
            newNode.neighbors.add(clone(neighbor));
        return newNode;
    }
}