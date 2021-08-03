/**
 * 剑指 Offer 37. 序列化二叉树
请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

示例：


输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
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

// 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
// 思路其实挺简单的，利用前序遍历来对二叉树进行序列化和反序列化
public class Codec {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(sb, root);
        return sb.toString();
    }

    public void dfs(StringBuilder sb, TreeNode node) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            dfs(sb, node.left);
            dfs(sb, node.right);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return helper(nodes);
    }

    public TreeNode helper(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals(NN)) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = helper(nodes);
            node.right = helper(nodes);
            return node;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));