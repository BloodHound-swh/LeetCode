/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。

 

示例:
给定如下二叉树，以及目标和 target = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]
 

提示：

节点总数 <= 10000
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

// 深度优先遍历加回溯
// 用前序遍历的方式访问到某一结点时，把该结点添加到路径上，并用目标值减去该节点的值。
// 如果该结点为叶结点并且目标值减去该节点的值刚好为0，则当前的路径符合要求，我们把加入res数组中。
// 如果当前结点不是叶结点，则继续访问它的子结点。当前结点访问结束后，递归函数将自动回到它的父结点。
// 因此我们在函数退出之前要在路径上删除当前结点，以确保返回父结点时路径刚好是从根结点到父结点的路径。
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target);
        return res;
    }

    public void dfs(TreeNode node, int target) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        target = target - node.val;
        if (target == 0 && node.left == null && node.right == null) {
            // 注意这样写是错误的 res.add(path);
            res.add(new ArrayList<>(path));
        }
        dfs(node.left, target);
        dfs(node.right, target);
        // 回溯
        path.remove(path.size() - 1); 
    }
}