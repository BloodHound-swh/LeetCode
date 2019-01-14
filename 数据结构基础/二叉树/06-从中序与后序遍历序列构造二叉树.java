/**
 * 从中序与后序遍历序列构造二叉树
根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
 */


// 递归
// 由于后序的顺序的最后一个肯定是根
// 所以原二叉树的根节点可以知道，题目中给了一个很关键的条件就是树中没有相同元素
// 有了这个条件我们就可以在中序遍历中也定位出根节点的位置，并以根节点的位置将中序遍历拆分为左右两个部分，分别对其递归调用原函数。
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(postorder, inorder, postorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode build(int[] post, int[] in, int postEnd, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        } 
        TreeNode root = new TreeNode(post[postEnd]);
        int index = 0;
        for (int i = inEnd; i >= inStart; i--) {
            if (root.val == in[i]) {
                index = i;
                break;
            }
        }
        root.right = build(post, in, postEnd - 1, index + 1, inEnd);
        root.left = build(post, in, postEnd - 1 + index - inEnd, inStart, index - 1); // postEnd - (inEnd - index) - 1
        return root;
        
    }
}