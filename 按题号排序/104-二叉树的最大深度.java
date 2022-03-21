/**
 * 二叉树的最大深度
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。
 */

// 未看答案版，没有做出来。不能熟练掌握递归
class Solution {
    public int maxDepth(TreeNode root) {
        int depth = 0;
        dfs(root, depth);
        return depth;
    }

    public void dfs(TreeNode root, int depth) {
        if (root.left == null && root.right == null)
            return;
        if (root.left != null) {
            depth++;
            dfs(root.left, depth);
        } else {
            depth++;
            dfs(root.right, depth);
        }
    }
}

// 很简单的递归思路，用max来代表最大深度，找好退出条件。
class Solution {

    public Integer max = 0;

    public int maxDepth(TreeNode root) {
        helper(root, 0);
        return max;
    }

    public void helper(TreeNode node, Integer tmp) {
        if (node == null) {
            return;
        }
        tmp++;
        max = Math.max(max, tmp);
        helper(node.left, tmp);
        helper(node.right, tmp);
    }
}

// 答案一
// 递归思想—：找到每一层所重复处理的方法，再找到边界条件，就可以确定递归。
// 树的最大深度等于左子树和右子树最大深度的最大值加1，以此递归即可，注意边界条件。
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }
}

// 答案二
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

// 第二次复习
class Solution {
    public int maxDepth(TreeNode root) {
        int maxDepth = dfs(root);
        
        return maxDepth;
        
    }
    
    public int dfs(TreeNode root) {
        if (root == null) 
            return 0;
        int left = dfs(root.left) + 1;
        int right = dfs(root.right) + 1;
        
        return Math.max(left, right);
    }
}