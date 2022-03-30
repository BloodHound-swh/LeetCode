/**
 * 114. 二叉树展开为链表
给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。
 

示例 1：


输入：root = [1,2,5,3,4,null,6]
输出：[1,null,2,null,3,null,4,null,5,null,6]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [0]
输出：[0]
 

提示：

树中结点数在范围 [0, 2000] 内
-100 <= Node.val <= 100
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

// 也不考虑什么复杂的实现了，就用递归把先序遍历用列表存下来，然后按照题意组装新二叉树即可
class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        helper(list, root);
        int size = list.size();
        // 注意这里怎么重新组装二叉树
        for (int i = 1; i < size; i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode curr = list.get(i);
            pre.left = null;
            pre.right = curr;
        }
    }

    public void helper(List<TreeNode> list, TreeNode node) {
        if (node == null) {
            return;
        }

        list.add(node);
        helper(list, node.left);
        helper(list, node.right);
    }
}