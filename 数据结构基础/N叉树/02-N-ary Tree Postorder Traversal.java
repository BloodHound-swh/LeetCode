/**
 * N-ary Tree Postorder Traversal
给定一个 N 叉树，返回其节点值的后序遍历。

例如，给定一个 3叉树 :

 



 

返回其后序遍历: [5,6,3,2,4,1].

 

说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */

// 递归
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new LinkedList<>();
        helper(res, root);
        return res;
    }

    public void helper(List<Integer> res, Node root) {
        if (root == null)
            return;
        for (Node node : root.children) {
            helper(res, node);
        }
        res.add(root.val);
    }
}

// 迭代
class Solution {
    public List<Integer> postorder(Node root) {
        if (root == null)
            return new LinkedList<Integer>();

        Stack<Node> s = new Stack<>();
        List<Integer> res = new LinkedList<>();

        s.push(root);

        while (!s.isEmpty()) {
            Node temp = s.pop();
            res.add(0, temp.val);
            for (Node node : temp.children) {
                s.push(node);
            }
        }

        return res;
    }
}