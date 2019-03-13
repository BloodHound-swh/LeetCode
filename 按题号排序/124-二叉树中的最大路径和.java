/**
 * 二叉树中的最大路径和

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

// 没思路，重点在于路径不能重复


// 对于某一个节点来说，路径和为自己的值加上左右节点返回值之和（左右节点返回值需与0）比较
// 对于该节点的父节点来说，由于路径不能重复，所以返回到父节点的路径只能是该节点的左子节点加自己，或者右子节点加自己向上返回
class Solution {
    int res;

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }

    public int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        res = Math.max(res, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}