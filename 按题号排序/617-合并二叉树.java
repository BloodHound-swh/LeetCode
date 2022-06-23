/**
 * 617. 合并二叉树
给你两棵二叉树： root1 和 root2 。

想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。

返回合并后的二叉树。

注意: 合并过程必须从两个树的根节点开始。

 

示例 1：


输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
输出：[3,4,5,5,4,null,7]
示例 2：

输入：root1 = [1], root2 = [1,2]
输出：[2,2]
 

提示：

两棵树中的节点数目在范围 [0, 2000] 内
-104 <= Node.val <= 10^4
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

// https://leetcode.cn/problems/merge-two-binary-trees/solution/dong-hua-yan-shi-di-gui-die-dai-617he-bing-er-cha-/ 
// 挨个遍历两颗二叉树中的每个节点，再把他们相加。用前序遍历就可以了
// 这两颗树并不是长得完全一样，有的树可能有左节点，但有的树没有。
// 对于这种情况，我们统一的都把他们挂到树 1 上面就可以了
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }

        return helper(root1, root2);
    }

    public TreeNode helper(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }

        node1.val += node2.val;
        node1.left = helper(node1.left, node2.left);
        node1.right = helper(node1.right, node2.right);

        return node1;
    }
}