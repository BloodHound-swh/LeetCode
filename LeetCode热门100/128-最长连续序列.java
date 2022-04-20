/**
 * 128. 最长连续序列
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

 

示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9
 

提示：

0 <= nums.length <= 10^5
-109 <= nums[i] <= 10^9
 */


// 将所有数都加入集合中，然后再遍历这些数，因为我们能O(1)的判断某个数是否在集合中，所以我们可以一个个向上或者向下检查。
// 为了避免之后重复检查，我们每查到一个数，都要将其从集合中移除。这样每遇到一个数，都检查它的上下边界，就能找出最长的连续数列。
// 时间复杂度仍是O(N)，因为我们不会检查不存在于数组的数，而存在于数组的数也只会检查一次。
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
                while (s.remove(pre)) {
                    pre--;
                }
                while (s.remove(next)) {
                    next++;
                }
                res = Math.max(res, next - pre - 1);
            }
        }
        return res;
    }
}


// 由于我们要枚举的数n一定是在数组中不存在前驱数n−1的，不然按照上面的分析我们会从n−1开始尝试匹配，因此我们每次在哈希表中检查是否存在n−1即能判断是否需要跳过了(当然也可以为了避免重复检查，直接从结合里去除）。
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int maxLen = 0;
        for (int n : nums) {
            if (!set.contains(n - 1)) {
                int tmpLen = 1;
                while (set.contains(++n)) {
                    tmpLen++;
                }
                maxLen = Math.max(tmpLen, maxLen);
            }
        }

        return maxLen;
    }
}