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

// https://www.youtube.com/watch?v=WbafSgetDDk&t=908s   FenwickTree
// 使用FenwickTree来记录比当前数字排名小的数字出现的次数
// 方法是用树状数组第i位表示排名第i大的数字是否出现，出现就用1，没出现就用0，所以update得delta = 1
// 时间复杂度O(nlogn)
class Solution {
    public static int lowbit(int x) {
        return x & (-x);
    }

    class FenwickTree {
        private int[] sums;

        public FenwickTree(int n) {
            sums = new int[n + 1];
        }

        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += lowbit(i);
            }
        }

        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= lowbit(i);
            }
            return sum;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < sorted.length; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1])
                ranks.put(sorted[i], rank++);
        }
        FenwickTree tree = new FenwickTree(ranks.size());
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int sum = tree.query(ranks.get(nums[i]) - 1);
            ans.add(tree.query(ranks.get(nums[i]) - 1));
            tree.update(ranks.get(nums[i]), 1);
        }
        Collections.reverse(ans);
        return ans;
    }
}

// BST 二叉树法
// 每个节点有三个值，第一个是自己的val，第二个记录这个数出现了几次，第三个记录比这个数小的数字有多少个
// 结合递归即可
class Solution {
    class Node {
        int val;
        int count;
        int left_count;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
            this.count = 1;
        }

        public int less_or_equal() {
            return count + left_count;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0)
            return ans;
        int n = nums.length;
        Node root = new Node(nums[n - 1]);
        ans.add(0);
        for (int i = n - 2; i >= 0; --i)
            ans.add(insert(root, nums[i]));
        Collections.reverse(ans);
        return ans;
    }

    private int insert(Node root, int val) {
        if (root.val == val) {
            ++root.count;
            return root.left_count;
        } else if (val < root.val) {
            ++root.left_count;
            if (root.left == null) {
                root.left = new Node(val);
                return 0;
            }
            return insert(root.left, val);
        } else {
            if (root.right == null) {
                root.right = new Node(val);
                return root.less_or_equal();
            }
            return root.less_or_equal() + insert(root.right, val);
        }
    }
}

// 二分法 时间复杂度O(n^2)
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(list, nums[i]);
            res[i] = index;
            list.add(index, nums[i]);
        }
        return Arrays.asList(res);
    }

    public int findIndex(List<Integer> list, int target) {
        if (list.size() == 0) 
            return 0;
        int start = 0;
        int end = list.size() - 1;
        if (list.get(end) < target) 
            return end + 1;
        if (list.get(start) >= target) 
            return 0;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (list.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (list.get(start) >= target) 
            return start;
        return end;
    }
}