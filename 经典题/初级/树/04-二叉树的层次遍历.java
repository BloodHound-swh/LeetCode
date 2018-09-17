/**
二叉树的层次遍历
给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
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


 //使用一个队列queue,按顺序装入每层的节点，queue.size()记录每层节点的个数。然后把每层的节点值add进list中，最后把一个个list放入打最终的list of list
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<size;i++){    // 这里的size每次都是上一层节点的个数
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)
                    queue.offer(node.right);
            }
            result.add(list);
        }
        return result;
    }
}

//使用类似前序遍历的方法，递归得到
//每次进入一层level，result添加一个ArrayList（因为需要顺序）。List存储该层的节点值。然后按顺序分别进入左右子节点，每次往下走一层时，level+1。
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        helper(result, 0, root);
        return result;
    }
    public static void helper(List<List<Integer>> result, int level, TreeNode root){
        if(root == null)
            return;
        if(level >= result.size())//或者 直接用==
            result.add(new ArrayList<Integer>());
        result.get(level).add(root.val);
        helper(result, level + 1, root.left);
        helper(result, level + 1, root.right);
    }
}