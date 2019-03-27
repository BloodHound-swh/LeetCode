/**
 * 最长连续递增序列
给定一个未经排序的整数数组，找到最长且连续的的递增序列。

示例 1:

输入: [1,3,5,4,7]
输出: 3
解释: 最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。 
示例 2:

输入: [2,2,2,2,2]
输出: 1
解释: 最长连续递增序列是 [2], 长度为1。
注意：数组长度不会超过10000。
 */


// 未看答案版，很不简洁。。。
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;

        int count = 0;
        int i = 1;
        while (i < nums.length) {
            int tmp = 1;
            while (i < nums.length && nums[i] > nums[i - 1]) {
                tmp++;
                i++;
            }
            count = Math.max(count, tmp);
            i++;
        }

        return count;
    }
}

// 优化一下
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 1;
        int curr = 1;
        for (int i = 1; i < nums.length; i++) {
            curr = nums[i] > nums[i - 1] ? curr + 1 : 1;
            max = Math.max(curr, max);
        }
        return max;
    }
}