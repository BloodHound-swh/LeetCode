/**
 * 路径总和
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

示例: 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
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

// 递归
// 首先，如果输入的是一个空节点，则直接返回false，如果如果输入的只有一个根节点，则比较当前根节点的值和参数sum值是否相同
// 若相同，返回true，否则false。 这个条件也是递归的终止条件。
// 下面我们就要开始递归了，由于函数的返回值是Ture/False，我们可以同时两个方向一起递归，中间用或||连接，只要有一个是True，整个结果就是True。
// 递归左右节点时，这时候的sum值应该是原sum值减去当前节点的值。
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

}



// 迭代
class Solution {
    public boolean hasPathSum(TreeNode node, int sum) {
        if (node == null) {
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                if (cur.val == sum) {
                    return true;
                }
            }
            if (cur.right != null) {
                cur.right.val += cur.val;
                stack.push(cur.right);
            }
            if (cur.left != null) {
                cur.left.val += cur.val;
                stack.push(cur.left);
            }
        }
        return false;
    }
}