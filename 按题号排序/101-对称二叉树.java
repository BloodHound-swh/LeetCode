/**
 * 对称二叉树
题目描述提示帮助提交记录社区讨论阅读解答
给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3
说明:

如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 */

// 未看答案没有做出，没有想出判定镜像的步骤

// 答案一
// 使用递归进行求解，先判断左右子结点是否相等，不等就返回false，相等就将左子结点的左子树与右子节点的右子树进行比较操作
// 同时将左子结点的右子树与右子结点的左子树进行比较，只有两个同时为真是才返回true，否则返回false。
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetrichelper(root.left, root.right);
    }

    public boolean isSymmetrichelper(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        if (left.val != right.val)
            return false;
        return isSymmetrichelper(left.left, right.right) && isSymmetrichelper(left.right, right.left);
    }
}

// 使用两个栈，
// stack1先压入left.left, 再压入left.right
// stack2先压入right.right,再压入right.left
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(root.left);
        s2.push(root.right);
        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode node1 = s1.pop();
            TreeNode node2 = s2.pop();
            if (node1 == null && node2 == null) {
                continue;
            } else if (node1 == null || node2 == null) {
                return false;
            } else if (node1.val != node2.val) {
                return false;
            }
            s1.push(node1.left);
            s2.push(node2.right);
            s1.push(node1.right);
            s2.push(node2.left);
        }

        return true;
    }
}