/**
 * 最长连续序列
给定一个未排序的整数数组，找出最长连续序列的长度。

要求算法的时间复杂度为 O(n)。

示例:

输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */



// 将所有数都加入集合中，然后再遍历这些数，因为我们能O(1)的判断某个数是否在集合中，所以我们可以一个个向上或者向下检查。
// 为了避免之后重复检查，我们每查到一个数，都要将其从集合中移除。这样每遇到一个数，都检查它的上下边界，就能找出最长的连续数列。
// 时间复杂度仍是O(N)，因为我们不会检查不存在于数组的数，而存在于数组的数也只会检查一次。
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int maxLen = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        for (int n : nums) {
            int curr = n, len = 0;
            while (set.contains(curr)) {
                set.remove(curr);
                curr++;
                len++;
            }
            curr = n - 1;
            while (set.contains(curr)) {
                set.remove(curr);
                curr--;
                len++;
            }
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }
}

// 优化
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = 0;
        HashSet<Integer> s = new HashSet<Integer>();
        for (int num : nums)
            s.add(num);
        for (int num : nums) {
            if (s.remove(num)) {
                int pre = num - 1, next = num + 1;
                while (s.remove(pre))
                    pre--;
                while (s.remove(next))
                    next++;
                res = Math.max(res, next - pre - 1);
            }
        }
        return res;
    }
}