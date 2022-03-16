/**
 * 102. 二叉树的层次遍历
题目描述提示帮助提交记录社区讨论阅读解答
给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// BFS经典做法，使用了一个队列
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                tmp.add(node.val);
            }
            res.add(tmp);
        }

        return res;
    }
}

// 使用类似前序遍历的方法，递归得到
// 每次进入一层level，result添加一个ArrayList（因为需要顺序）。List存储该层的节点值。然后按顺序分别进入左右子节点，每次往下走一层时，level+1。
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        helper(result, 0, root);
        return result;
    }

    public static void helper(List<List<Integer>> result, int level, TreeNode root) {
        if (root == null)
            return;
        if (level >= result.size())// 或者 直接用==
            result.add(new ArrayList<Integer>());
        result.get(level).add(root.val);
        helper(result, level + 1, root.left);
        helper(result, level + 1, root.right);
    }
}