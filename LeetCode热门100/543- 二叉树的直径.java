/**
 * 543. 二叉树的直径
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

 

示例 :
给定二叉树

          1
         / \
        2   3
       / \     
      4   5    
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

 

注意：两结点之间的路径长度是以它们之间边的数目表示。
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
// 双重递归，计算每个节点的左右子节点的最大深度和
class Solution {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int res = helper(root.left, 0) + helper(root.right, 0);
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(res, left), right);
    }

    public int helper(TreeNode node, int deep) {
        if (node == null) {
            return 0;
        }

        int leftDeep = helper(node.left, deep);
        int rightDeep = helper(node.right, deep);

        return Math.max(leftDeep, rightDeep) + 1;
    }
}

// 优化双重递归，在单次递归中计算res
class Solution {

    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        maxDepth(root);
        return res;
    }

    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDeep = maxDepth(node.left);
        int rightDeep = maxDepth(node.right);
        // 将每个节点最大直径(左子树深度+右子树深度)当前最大值比较并取大者
        res = Math.max(res, leftDeep + rightDeep);
        // 返回节点深度
        return Math.max(leftDeep, rightDeep) + 1;
    }
}