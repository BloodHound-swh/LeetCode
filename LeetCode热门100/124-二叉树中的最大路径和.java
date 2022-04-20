/**
 * 124.二叉树中的最大路径和

给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

示例 1:

输入: [1,2,3]

       1
      / \
     2   3

输出: 6
示例 2:

输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出: 42
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

// 递归，用res来记录最大值
class Solution {

    public Integer res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return res;
    }

    public int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, helper(node.left));
        int right = Math.max(0, helper(node.right));

        // 更新结果res，此时相当于不会继续向父节点走动
        res = Math.max(res, node.val + left + right);

        // 若继续向父节点走动，由于路径是不能返回重复走的，因此只能是当前的左右节点中的最大贡献值加当前节点自己的值
        return Math.max(left, right) + node.val;
    }
}