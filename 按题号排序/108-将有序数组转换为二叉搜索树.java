/**
 * 将有序数组转换为二叉搜索树

将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
 */


// 未看答案没有写出来，没有意识到这种找根节点的题可以使用二分法



// 答案
// 因为是有序数组，所以使用二分法，先把有序数组的最中间值设为根结点，然后根结点的左孩子是左半段数组的最中间值，根结点的右孩子是右半段数组的最中间值。
// 依次这样，即可以建立一个平衡二叉搜索树。
// 用这个方法做出的答案是[0,-10,5,null,-3,null,9]
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null)
            return null;
        TreeNode root = helper(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode helper(int[] nums, int min, int max) {
        if (min > max)
            return null;
        int mid = (min + max) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, min, mid - 1);
        root.right = helper(nums, mid + 1, max);
        return root;
    }
}

// 另一种写法
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null)
            return null;
        TreeNode root = helper(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode helper(int[] nums, int min, int max) {
        if (min <= max) {
            int mid = (min + max) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, min, mid - 1);
            root.right = helper(nums, mid + 1, max);
            return root;
        } else {
            return null;
        }
    }
}