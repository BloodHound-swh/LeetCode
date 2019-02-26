/**
 * N叉树的层序遍历
Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a 3-ary tree:

 



 

We should return its level order traversal:

[
     [1],
     [3,2,4],
     [5,6]
]
 */


// 迭代（BFS）
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null)
            return res;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                list.add(node.val);
                for (Node child : node.children) {
                    q.offer(child);
                }
            }
            res.add(list);
        }

        return res;
    }
}

// 递归
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }

    private void helper(Node node, int level, List<List<Integer>> res) {
        if (node == null)
            return;

        if (res.size() == level)
            res.add(new ArrayList<>());

        res.get(level).add(node.val);
        for (Node child : node.children)
            helper(child, level + 1, res);
    }
}