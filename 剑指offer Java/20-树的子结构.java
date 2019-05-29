/**
 * 题目描述
输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */


// 递归思想，如果根节点相同则递归调用IsSubtree()，如果根节点不相同，则判断root1的左子树和roo2是否相同，再判断右子树和root2是否相同;
// 注意节点为空的条件，HasSubTree中，只要有树为空就返回false; IsSubtree中，要先判断root2，如果root2为空，则说明第二棵树遍历完了，即匹配成功。
public class Solution {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;

        return IsSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    public boolean IsSubtree(TreeNode node1, TreeNode node2) {
        if (node2 == null)
            return true;
        if (node1 == null)
            return false;

        if (node1.val == node2.val) {
            return IsSubtree(node1.left, node2.left) && IsSubtree(node1.right, node2.right);
        } else {
            return false;
        }
    }
}