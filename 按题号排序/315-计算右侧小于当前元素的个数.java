/**
 * 计算右侧小于当前元素的个数

给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例:

输入: [5,2,6,1]
输出: [2,1,1,0] 
解释:
5 的右侧有 2 个更小的元素 (2 和 1).
2 的右侧仅有 1 个更小的元素 (1).
6 的右侧有 1 个更小的元素 (1).
1 的右侧有 0 个更小的元素.
 */


// 这种和其他节点有关的题应该首先想到二叉树结构


// BST 二叉树法
// 每个节点有三个值，第一个是自己的val，第二个count记录这个数出现了几次，第三个smaller记录比这个数小的数字有多少个
// 结合递归即可
class Solution {
    class Node {
        int val;
        int count;
        int smaller;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
            this.count = 1;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;

        int n = nums.length;
        Node root = new Node(nums[n - 1]);
        res.add(0);
        for (int i = n - 2; i >= 0; i--) {
            res.add(insert(root, nums[i]));
        }

        Collections.reverse(res);
        return res;
    }

    public int insert(Node root, int val) {
        if (root.val == val) {
            root.count++;
            return root.smaller;
        } else if (val < root.val) {
            root.smaller++;
            if (root.left == null) {
                root.left = new Node(val);
                return 0;
            }
            return insert(root.left, val);
        } else {
            if (root.right == null) {
                root.right = new Node(val);
                return root.smaller + root.count;
            }
            return root.smaller + root.count + insert(root.right, val);
        }
    }
}