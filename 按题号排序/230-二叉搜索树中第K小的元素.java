/**
 * 二叉搜索树中第K小的元素

给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

说明：
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
进阶：
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？


 */

// 未看答案没有写出，因为如何利用一个变量来确定第k小的数不会。。。

/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//  答案一
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode p = root;
        int res = 0;

        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode t = stack.pop();
                k--;
                if (k == 0)
                    res = t.val;
                p = t.right;
            }
        }

        return res;
    }
}

// 答案二
// 递归
// 同一思路，不过此法使用了全局变量
class Solution {
    int cnt, res;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) 
            return 0;
        kthSmallest(root.left, k);
        if(++cnt == k) 
            res = root.val;
        kthSmallest(root.right, k);
        return res;
    }
}