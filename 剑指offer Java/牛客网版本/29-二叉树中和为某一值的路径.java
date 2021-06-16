/**
 * 题目描述
输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */


// 用前序遍历的方式访问到某一结点时，把该结点添加到路径上，并用目标值减去该节点的值。
// 如果该结点为叶结点并且目标值减去该节点的值刚好为0，则当前的路径符合要求，我们把加入res数组中。
// 如果当前结点不是叶结点，则继续访问它的子结点。当前结点访问结束后，递归函数将自动回到它的父结点。
// 因此我们在函数退出之前要在路径上删除当前结点，以确保返回父结点时路径刚好是从根结点到父结点的路径。
public class Solution {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> tmp = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null)
            return res;
        target -= root.val;
        tmp.add(root.val);
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<Integer>(tmp));
        } else {
            FindPath(root.left, target);
            FindPath(root.right, target);
        }
        tmp.remove(tmp.size() - 1);

        return res;
    }
}