/**
 * 存在重复元素 III
给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。

示例 1:

输入: nums = [1,2,3,1], k = 3, t = 0
输出: true
示例 2:

输入: nums = [1,0,1,1], k = 1, t = 2
输出: true
示例 3:

输入: nums = [1,5,9,1,5,9], k = 2, t = 3
输出: false
 */


// 要求判断之前是否存在差值小于t的数字，我们需要知道在当前数字x两边的数字，即最大的小于x的数字和最小的大于x的数字。
// 二叉搜索树有也有这样的性质，它的左子树的最右节点是最大的较小值，右子树的最左节点是最小的较大值。
// 这里我们用TreeSet这个类，它实现了红黑树，并有集合的性质，非常适合这题。
// 我们同样也是维护一个大小为k的TreeSet，多余k个元素时把最早加入的给删除。用ceiling()和floor()可以找到最小的较大值和最大的较小值。
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (set.ceiling(x) != null && set.ceiling(x) <= t + x)
                return true;
            if (set.floor(x) != null && x <= t + set.floor(x))
                return true;

            set.add(x);
            if (set.size() > k)
                set.remove(nums[i - k]);
        }
        return false;
    }
}