/**
 * 题目描述
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */

// https://blog.csdn.net/u013132035/article/details/80638812
public class Solution {
    TreeNode pre = null;
    TreeNode head = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        inorder(pRootOfTree);
        return head;
    }
    
    public void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        root.left = pre;
        if (pre != null)
            pre.right = root;
        pre = root;
        if (head == null)
            head = root;
        inorder(root.right);
    }
}