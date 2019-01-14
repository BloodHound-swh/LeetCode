/**
中序遍历二叉树
给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 //递归
 class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        if (root == null) return res;
        DFS(root,res);
        return res;
    }
    
    public void DFS(TreeNode node, LinkedList<Integer> list) {
        if (node == null) return;
        DFS(node.left, list);
        list.add(node.val);
        DFS(node.right, list);
    }
}

//迭代
// 使用一个栈来存储二叉树节点，根据中序遍历的规则，我们可以推算出这样的规律： 
// 1. 将当前非空节点入栈 
// 2. 如果左子节点不为空，则继续将左子节点入栈 
// 3. 如果左子节点为空，则抛出栈顶节点并记录 val 值，然后将其右子节点入栈 
// 4. 重复 1、2、3 步骤直至栈空
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}