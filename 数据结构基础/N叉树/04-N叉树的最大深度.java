/**
 * Maximum Depth of N-ary Tree
给定一个 N 叉树，找到其最大深度。

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

例如，给定一个 3叉树 :

 



 

我们应返回其最大深度，3。

说明:

树的深度不会超过 1000。
树的节点总不会超过 5000。
 */

// 递归
class Solution {
    public int maxDepth(Node root) {
        if (root == null)
            return 0;
        int res = 1;
        for (Node node : root.children) {
            res = Math.max(res, maxDepth(node) + 1);
        }
        return res;
    }
}

// 迭代
// 其实就是层序遍历
class Solution {
    public int maxDepth(Node root) {
        if (root == null)
            return 0;
        int res = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                for (Node child : node.children) {
                    q.offer(child);
                }
            }
            res++;
        }

        return res;
    }
}