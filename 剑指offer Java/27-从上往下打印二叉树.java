/**
 * 题目描述
从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */

// 同LeetCode第102题
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                res.add(tmp.val);
                if (tmp.left != null)
                    queue.offer(tmp.left);
                if (tmp.right != null)
                    queue.offer(tmp.right);
            }
        }
        
        return res;
    }
}