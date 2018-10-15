/**
 * 数组拆分 I
给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。

示例 1:

输入: [1,4,3,2]

输出: 4
解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
提示:

n 是正整数,范围在 [1, 10000].
数组中的元素范围在 [-10000, 10000].
 */

// 要让每组的两个数字尽量接近，这样才不会浪费更大的数字
// 因此可以先将数组排序，然后每个两位取出相加即可
class Solution {
    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }
}

// 既然考虑到排序，何不考虑桶排序，Space instead of Time
// 开一个两万范围的桶
// hua hua说这是计数排序
class Solution {
    public int arrayPairSum(int[] nums) {
        int[] hash = new int[20001];
        for (int i = 0; i < nums.length; i++)
            hash[nums[i] + 10000]++;
        int sum = 0;
        // 因为2n一定是偶数,所以从左往右先累加小的,odd用来控制,间隔一个再累加
        boolean odd = true;
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                // 从左往右,被累加上的一定是min
                if (odd)
                    sum += i - 10000;
                odd = !odd;
                hash[i]--;
            }
        }
        return sum;
    }

}