/**
 * 平衡二叉树
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

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
 */

// 非平衡的条件是有两个叶子节点的深度相差大于1，最直接的想法就是把左子树和右子树的高度都算出来
// 如果相差大于1则说明不是平衡的。在递归中，从叶子结点开始一层层返回高度，叶子结点是1。
// 我们返回-1代表非平衡，返回自然数代表有效的子树高度。
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return (treeHeight(root) != -1);
    }

    public int treeHeight(TreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = treeHeight(root.left);
        int rightHeight = treeHeight(root.right);
        if (leftHeight == -1 || rightHeight == -1)
            return -1;
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return (Math.max(leftHeight, rightHeight) + 1);
    }
}

// 上面那个方法正确但不是很高效，因为每一个点都会被上面的点计算深度时访问一次，我们可以进行优化。
// 方法是如果我们发现子树不平衡，则不计算具体的深度，而是直接返回-1。
// 那么优化后的方法为：对于每一个节点，我们通过checkDepth方法递归获得左右子树的深度，如果子树是平衡的，则返回真实的深度，若不平衡，直接返回-1
class Solution {
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null)
            return 0;

        int left = depth(root.left);
        if (left == -1)
            return -1;
        int right = depth(root.right);
        if (right == -1)
            return -1;

        if (Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }
}