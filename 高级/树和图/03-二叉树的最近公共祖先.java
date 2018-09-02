/**
 * 二叉树的最近公共祖先
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。
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

// 用深度优先搜索，从叶子节点向上，标记子树中出现目标节点的情况。如果子树中有目标节点，标记为那个目标节点，如果没有，标记为null。
// 显然，如果左子树、右子树都有标记，说明就已经找到最小公共祖先了。
// 最后，解决节点都在某一侧的情况
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 发现目标节点则通过返回值标记该子树发现了某个目标结点
        if (root == null || root == p || root == q)
            return root;
        // 查看左子树中是否有目标结点，没有为null
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 查看右子树是否有目标节点，没有为null
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 都不为空，说明做右子树都有目标结点，则公共祖先就是本身
        if (left != null && right != null)
            return root;
        // 如果节点都在某一侧的情况
        return left == null ? right : left;
    }
}