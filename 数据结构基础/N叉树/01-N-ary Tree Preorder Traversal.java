/**
 * N-ary Tree Preorder Traversal
给定一个 N 叉树，返回其节点值的前序遍历。

例如，给定一个 3叉树 :

 



 

返回其前序遍历: [1,3,5,6,2,4]。

 

说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

// 递归
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        helper(res, root);
        return res;
    }

    public void helper(List<Integer> res, Node root) {
        if (root == null)
            return;
        res.add(root.val);
        for (Node node : root.children) {
            helper(res, node);
        }
    }
}

// 迭代
// 使用栈stack来辅助，需要注意的是，如果使用栈的话，我们遍历子结点数组的顺序应该是从后往前的，因为栈是后进先出的顺序，所以需要最先遍历的子结点应该最后进栈
class Solution {
    public List<Integer> preorder(Node root) {
        if (root == null)
            return new LinkedList<Integer>();

        Stack<Node> s = new Stack<>();
        List<Integer> res = new LinkedList<>();

        s.push(root);

        while (!s.isEmpty()) {
            Node temp = s.pop();
            res.add(temp.val);
            int size = temp.children.size();
            for (int i = size - 1; i >= 0; i--) {
                s.push(temp.children.get(i));
            }
        }

        return res;
    }
}