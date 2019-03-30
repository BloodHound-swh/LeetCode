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

// 未看答案没有做出

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
// 使用一个list来存储每层元素，使用clist来存储每层的输出答案，leftToRight作为标志位来决定顺序
// 当leftToRight位true，则先左后右存储下一层的元素在list的末尾，否则先右后左的存储下一层元素在list的开头
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> dq = new LinkedList<>(); // 注意双向队列的实现和Queue一样也是LinkedList
        dq.offer(root);
        boolean leftToRight = true;
        
        while (!dq.isEmpty()) {
            int size = dq.size();
            List<Integer> list = new ArrayList<>();
            if (leftToRight) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = dq.poll();
                    list.add(node.val);
                    if (node.left != null)
                        dq.offer(node.left);
                    if (node.right != null)
                        dq.offer(node.right);
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = dq.pollLast();
                    list.add(node.val);
                    if (node.right != null) // 注意这里是先放right,且放在首端
                        dq.offerFirst(node.right);
                    if (node.left != null)
                        dq.offerFirst(node.left);
                }
            }
            res.add(list);
            leftToRight = !leftToRight;
        }
        
        return res;
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