/**
 * 验证二叉搜索树
题目描述提示帮助提交记录社区讨论阅读解答
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
*/

// 未看答案没有做出来，多种边界条件的递归不熟，同时对于中序遍历不熟练

// 答案一
// 这道题设定为左<根<右，那么就可以用中序遍历来做。
// 先递归左子树一直到最后一个左节点，返回到当前的root节点，与pre比较，大于等于pre后，把root赋值给pre，再比较右子节点与pre
class Solution {
    private TreeNode prev;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return inOrder(root);
    }

    private boolean inOrder(TreeNode root) {
        if (root == null)
            return true;
        if (!inOrder(root.left))
            return false;
        if (prev != null && root.val <= prev.val)
            return false;
        prev = root;
        return inOrder(root.right);
    }
}

// 答案二
// 查看每个parent-child关系。同时把root level上面传下来max,min界限定住。在递归过程中换成它们自己的节点值
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return helper(root, null, null);
    }

    public boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        if ((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;
        boolean left = helper(root.left, min, root.val);
        boolean right = helper(root.right, root.val, max);
        return left && right;
    }
}

// 答案三，思想同答案二
// 代码优化，其实依然可以用null作为初始。
// 用long代替int就是为了包括int的边界条件
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean helper(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val < max && root.val > min && helper(root.left, min, root.val) && helper(root.right, root.val, max)) {
            return true;
        }
        return false;
    }
}