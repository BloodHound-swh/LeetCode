/**
 * 剑指 Offer 55 - I. 二叉树的深度
输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

例如：

给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

 

提示：

节点总数 <= 10000
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
// 很容易想到递归
class Solution {
    int res = 0, deep = 0;
    public int maxDepth(TreeNode root) {
        dfs(root);
        return res;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            res = Math.max(res, deep);
            return;
        }
        deep++;
        dfs(node.left);
        dfs(node.right);
        // 注意这里的回撤减1，代表这个节点以及其子节点均不考虑了
        deep--;
    }
    
}

// 大神的题解就是简洁
// 树的深度等于左子树的深度与右子树的深度中的最大值+1。
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

// 或者这样写容易理解一点
class Solution {
    public int maxDepth(TreeNode root) {
        int maxDepth = dfs(root);
        
        return maxDepth;
        
    }
    
    public int dfs(TreeNode root) {
        if (root == null) 
            return 0;
        int left = dfs(root.left) + 1;
        int right = dfs(root.right) + 1;
        
        return Math.max(left, right);
    }
}

// 层序遍历（BFS)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
			return 0;
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		int res = 0; // 0代表层数
		while (!q.isEmpty()) {
			res++;
			int n = q.size();
			for (int i = 0; i < n; i++) {
				TreeNode node = q.poll();
				if (node.left != null) {
					q.add(node.left);
				}
				if (node.right != null) {
					q.add(node.right);
				}
			}
		}
		return res;
    }
}
