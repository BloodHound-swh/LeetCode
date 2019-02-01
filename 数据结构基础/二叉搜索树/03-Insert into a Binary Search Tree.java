/**
 * Insert into a Binary Search Tree
给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

例如, 

给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和 插入的值: 5
你可以返回这个二叉搜索树:

         4
       /   \
      2     7
     / \   /
    1   3 5
或者这个树也是有效的:

         5
       /   \
      2     7
     / \   
    1   3
         \
          4
 */

// 使用递归
// 在递归函数中，首先判断当前结点是否为空，为空的话就新建一个结点返回。
// 否则就判断当前结点值是否大于目标值，是的话就对左子结点调用递归函数，并将返回值赋给当前结点的左子结点，
// 否则就对右子结点调用递归函数，并将返回值赋给当前结点的右子结点，最后返回当前结点即可
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        helper(root, val);
        return root;
    }

    public void helper(TreeNode root, int val) {
        if (root.val < val) {
            if (root.right == null) {
                TreeNode node = new TreeNode(val);
                root.right = node;
                return;
            } else {
                helper(root.right, val);
            }
        } else if (root.val > val) {
            if (root.left == null) {
                TreeNode node = new TreeNode(val);
                root.left = node;
                return;
            } else {
                helper(root.left, val);
            }
        }
    }
}

// 简化代码
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        else
            root.left = insertIntoBST(root.left, val);
        return root;
    }
}

// 递归
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        TreeNode dummy = root;
        while (root != null) {
            if (node.val < root.val) {
                if (root.left == null) {
                    root.left = node;
                    break;
                }
                root = root.left;
            } else if (node.val > root.val) {
                if (root.right == null) {
                    root.right = node;
                    break;
                }
                root = root.right;
            }
        }
        return dummy;
    }
}