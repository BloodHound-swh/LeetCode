/**
 * 从前序与中序遍历序列构造二叉树

根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
 */

// 未看答案时，递归没有头绪。。。

/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 答案一
// https://www.youtube.com/watch?v=S1wNG5hx-30
// 使用pre-st来从preorder中找到根节点，用in_st从inorder中找到左子树的起始位置，in_end找到结束位置
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length)
            return null;
        return buildTreeHelper(preorder, inorder, 0, 0, preorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int pre_st, int in_st, int in_end) {
        if (pre_st > preorder.length || in_st > in_end) {
            return null;
        }

        TreeNode current = new TreeNode(preorder[pre_st]);
        int i = in_st;
        while (i < in_end) {
            if (inorder[i] == preorder[pre_st])
                break;
            i++;
        }
        current.left = buildTreeHelper(preorder, inorder, pre_st + 1, in_st, i - 1);
        current.right = buildTreeHelper(preorder, inorder, pre_st + (i - in_st + 1), i + 1, in_end);
        return current;
    }
}

// 答案二
// 换一种写法
class Solution {
    // 写法仿照 剑指offer 面试题7 重建二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0) {
            return null;
        }
        return buildCore(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildCore(int[] preorder, int preSt, int preEnd, int[] inorder, int inSt, int inEnd) {
        // 前序遍历第一个节点是根节点
        int rootValue = preorder[preSt];
        TreeNode root = new TreeNode(rootValue);

        // 前序序列只有根节点
        if (preSt == preEnd) {
            return root;
        }
        // 遍历中序序列，找到根节点的位置
        int rootInorder = inSt;
        while (inorder[rootInorder] != rootValue && rootInorder <= inEnd) {
            rootInorder++;
        }

        // 左子树的长度
        int leftLength = rootInorder - inSt;
        // 前序序列中左子树的最后一个节点
        int leftPreEnd = preSt + leftLength;

        // 左子树非空
        if (leftLength > 0) {
            // 重建左子树
            root.left = buildCore(preorder, preSt + 1, leftPreEnd, inorder, inSt, inEnd);
        }
        // 右子树非空
        if (leftLength < preEnd - preSt) {
            // 重建右子树
            root.right = buildCore(preorder, leftPreEnd + 1, preEnd, inorder, rootInorder + 1, inEnd);
        }
        return root;
    }
}

// 官方题解
class Solution {
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}