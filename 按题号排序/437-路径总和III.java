/**
 * 路径总和 III
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

 

示例 1：



输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
示例 2：

输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3
 

提示:

二叉树的节点个数的范围是 [0,1000]
-109 <= Node.val <= 10^9 
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// 双重递归
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        // 从根节点作为起点，计算路径数
        int res = helper(root, targetSum);
        // 从左节点作为起点，计算路径个数
        res += pathSum(root.left, targetSum);
        // 从左节点作为起点，计算路径个数
        res += pathSum(root.right, targetSum);

        return res;
    }

    public int helper(TreeNode node, int targetSum) {
        if (node == null) {
            return 0;
        } 

        int res = 0;
        if (node.val == targetSum) {
            res++;
        }

        res += helper(node.left, targetSum - node.val);
        res += helper(node.right, targetSum - node.val);

        return res;
    }
}

// https://leetcode.cn/problems/path-sum-iii/solution/dui-qian-zhui-he-jie-fa-de-yi-dian-jie-s-dey6/
// 一个节点的前缀和就是该节点到根之间的路径和, 两节点间的路径和 = 两节点的前缀和之差
// 我们只用遍历整颗树一次，记录每个节点的前缀和，并查询该节点的祖先节点中符合条件的个数，将这个数量加到最终结果上。
// HashMap的key是前缀和， value是该前缀和的节点数量
// 状态恢复代码的作用就是： 在遍历完一个节点的所有子节点后，将其从map中除去。
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> prefix = new HashMap<Integer, Integer>();
        // 前缀树为0的个数至少是一个
        prefix.put(0, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Integer, Integer> prefix, int curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        // 得到当前前缀树的值
        curr += root.val;
        // 得到我们想要前缀树的个数，想要的前缀树值就是当前前缀树值减去目标值
        ret = prefix.getOrDefault(curr - targetSum, 0);
        // 将当前前缀树的值个数保存
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);

        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        // 防止左边前缀树影响右边前缀树，左边前缀树可能有个为6，右边正好想要一个前缀树为6的，这样子就出错了
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }
}