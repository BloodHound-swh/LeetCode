/**
 * 剑指 Offer 55 - II. 平衡二叉树
输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

 

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。

 

限制：

0 <= 树的结点个数 <= 10000
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

// 后序遍历 + 剪枝，这里有一个小技巧，当不符合条件时直接返回-1，避免boolean和深度int绕在一起
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root) != -1;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDeepth = dfs(node.left);
        int rightDeepth = dfs(node.right);

        if (Math.abs(leftDeepth - rightDeepth) > 1 || leftDeepth == -1 || rightDeepth == -1) {
            return -1;
        } else {
            return Math.max(leftDeepth, rightDeepth) + 1;
        }
    }
}

class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root) != -1;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDeep = dfs(node.left);
        if (leftDeep == -1) {
            return -1;
        }
        int rightDeep = dfs(node.right);
        if (rightDeep == -1) {
            return -1;
        }
        if (Math.abs(leftDeep - rightDeep) > 1) {
            return -1;
        }

        return Math.max(leftDeep, rightDeep) + 1;
    }
}