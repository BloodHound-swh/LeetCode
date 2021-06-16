/**
 * 题目描述
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */


// 前序遍历的第一个节点就是树的根节点，所以我们先根据前序遍历序列的第一个数字创建根结点，接下来在中序遍历序列中找到根结点的位置
// 根节点的左边就是左子树，右边就是右子树，这样就能确定左、右子树结点的数量。
// 在前序遍历和中序遍历的序列中划分了左、右子树结点的值之后，就可以递归地去分别构建它的左右子树。
public class Solution {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0)
            return null;

        return helper(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode helper(int[] pre, int pre_st, int pre_end, int[] in, int in_st, int in_end) {
        if (pre_st > pre_end || in_st > in_end)
            return null;

        TreeNode root = new TreeNode(pre[pre_st]);
        int idx = in_st;
        while (idx <= in_end) {
            if (in[idx] == pre[pre_st])
                break;
            idx++;
        }

        int leftLength = idx - in_st;

        root.left = helper(pre, pre_st + 1, pre_st + leftLength, in, in_st, idx - 1);
        root.right = helper(pre, pre_st + leftLength + 1, pre_end, in, idx + 1, in_end);

        return root;
    }
}