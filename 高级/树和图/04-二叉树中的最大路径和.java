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

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

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

// 方法二，思路其实同方法一
// 首先我们分析一下对于指定某个节点为根时，最大的路径和有可能是哪些情况。
// 第一种是左子树的路径加上当前节点
// 第二种是右子树的路径加上当前节点
// 第三种是左右子树的路径加上当前节点（相当于一条横跨当前节点的路径
// 第四种是只有自己的路径
// 如果当前节点上面还有节点，那它的父节点是不能累加第三种情况的。
// 所以我们要计算两个最大值，一个是当前节点下最大路径和，另一个是如果要连接父节点时最大的路径和。我们用前者更新全局最大量，用后者返回递归值就行了。
class Solution {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    public int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        // 连接父节点的最大路径是一、二、四这三种情况的最大值
        int currSum = Math.max(Math.max(left + root.val, right + root.val), root.val);
        // 当前节点的最大路径是一、二、三、四这四种情况的最大值
        int currMax = Math.max(currSum, left + right + root.val);
        // 用当前最大来更新全局最大
        max = Math.max(currMax, max);
        return currSum;
    }
}