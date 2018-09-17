/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 //查看每个parent-child关系。同时把root level上面传下来max,min界限定住。在递归过程中换成它们自己的节点值
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        return helper(root, null, null);
    }
    public boolean helper(TreeNode root, Integer min, Integer max){
        if(root == null)
            return true;
        if((min!=null&&root.val<=min)||(max!=null&&root.val>=max))
            return false;
        boolean left = helper(root.left, min, root.val);
        boolean right = helper(root.right, root.val, max);
        return left && right;
    }
}

//代码优化，其实依然可以用null作为初始。
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean helper(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val < max && root.val > min && 
            helper(root.left, min, root.val) &&
            helper(root.right, root.val, max)) {
                return true;
        } 
        return false;
    }
}

//这道题设定为左<根<右，那么就可以用中序遍历来做。
//先递归左子树一直到最后一个左节点，返回到当前的root节点，与pre比较，大于等于pre后，把root赋值给pre，再比较右子节点与pre
class Solution {
    private TreeNode prev;
    public boolean isValidBST(TreeNode root) {
      prev = null;
      return inOrder(root);
    }
    
    private boolean inOrder(TreeNode root) {
      if (root == null) 
        return true;
      if (!inOrder(root.left)) 
        return false;
      if (prev != null && root.val <= prev.val) 
        return false;
      prev = root;
      return inOrder(root.right);
    }
  }