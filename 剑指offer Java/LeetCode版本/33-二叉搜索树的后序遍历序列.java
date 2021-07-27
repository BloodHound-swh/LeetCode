/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

 

参考以下这颗二叉搜索树：

     5
    / \
   2   6
  / \
 1   3
示例 1：

输入: [1,6,3,2,5]
输出: false
示例 2：

输入: [1,3,2,6,5]
输出: true
 

提示：

数组长度 <= 1000
 */


// 对于后序遍历来说，序列数组的最后一个元素一定是根节点, 根据这个元素，将前面的数组分为左、右两个部分
// 左侧部分都比该元素小，右侧部分都比该元素大，如果右侧部分有比该根节点小的元素，那么就不是后序遍历，如此递归进行。
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return true;
        }

        if (postorder.length == 1) {
            return true;
        }

        return helper(postorder, 0, postorder.length - 1);
    }

    public boolean helper(int[] postorder, int left, int root) {
        if (left > root) {
            return true;
        }

        int start = left;
        while (postorder[root] > postorder[start]) {
            start++;
        }

        for (int i = start; i < root; i++) {
            if (postorder[i] < postorder[root]) {
                return false;
            }
        }

        return helper(postorder, left, start - 1) && helper(postorder, start, root - 1);
    }
}