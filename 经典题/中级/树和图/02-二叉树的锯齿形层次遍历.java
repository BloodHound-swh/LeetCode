/**
 * 二叉树的锯齿形层次遍历
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层次遍历如下：

[
  [3],
  [20,9],
  [15,7]
]
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 使用一个list来存储每层元素，使用clist来存储每层的输出答案，leftToRight作为标志位来决定顺序
// 当leftToRight位true，则先左后右存储下一层的元素在list的末尾，否则先右后左的存储下一层元素在list的开头
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (root == null) return result;
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        boolean leftToRight = true;
        list.add(root);
        
        while(!list.isEmpty()){
            int size = list.size();
            LinkedList<Integer> clist = new LinkedList<Integer>();
            if (leftToRight) {
                for (int i = 0; i < size; i++) {
                    TreeNode curr = list.remove(0);
                    clist.add(curr.val);
                    if (curr.left != null) list.add(curr.left);
                    if (curr.right != null) list.add(curr.right);
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode curr = list.remove(list.size() - 1);
                    clist.add(curr.val);
                    if (curr.right != null) list.add(0, curr.right);
                    if (curr.left != null) list.add(0, curr.left);
                }
            }
            result.add(clist);
            leftToRight = !leftToRight;
        }
        return result;
    }
}

//递归，思路和方法一差不多
class Solution {
	private void dfs(TreeNode root, int level, List<List<Integer>> ret) {
		if (root == null) {
			return;
		}
		if (ret.size() < level + 1) {
			ret.add(new ArrayList());
		}
		if (level % 2 == 0) {
			ret.get(level).add(root.val);
		} else {
			ret.get(level).add(0, root.val);
		}
		dfs(root.left, level + 1, ret);
		dfs(root.right, level + 1, ret);
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		dfs(root, 0, ret);
		return ret;
	}
}