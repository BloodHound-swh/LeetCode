/**
 * 寻找重复的子树
给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。

两棵树重复是指它们具有相同的结构以及相同的结点值。

示例 1：

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
下面是两个重复的子树：

      2
     /
    4
和

    4
因此，你需要以列表的形式返回上述重复子树的根结点。
 */


// 用到了后(前?)序遍历，还有数组序列化，并且建立序列化跟其出现次数的映射
// 这样如果我们得到某个结点的序列化字符串，而该字符串正好出现的次数为1，说明之前已经有一个重复树了
// 我们将当前结点存入结果res，这样保证了多个重复树只会存入一个结点
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        serialize(root, res, map);
        return res;
    }

    public String serialize(TreeNode root, List<TreeNode> res, Map<String, Integer> map) {
        if (root == null)
            return "#";
        String key = root.val + "," + serialize(root.left, res, map) + "," + serialize(root.right, res, map);
        if (!map.containsKey(key)) {
            map.put(key, 1);
        } else if (map.get(key) == 1) {
            res.add(root);
            map.put(key, map.get(key) + 1);
        }
        return key;
    }
}