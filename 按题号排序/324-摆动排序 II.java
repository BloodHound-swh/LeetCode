/**
 * 摆动排序 II

给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

示例 1:

输入: nums = [1, 5, 1, 1, 6, 4]
输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
示例 2:

输入: nums = [1, 3, 2, 2, 3, 1]
输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
说明:
你可以假设所有输入都会得到有效的结果。

进阶:
你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */



// 没什么好思路，想要达成进阶就更难了。


// 可以先将数组排序，因为题目要求最终的结果每个奇数位的数字都比它周围两个偶数位的数字大
// 所以只需从mid和end开始，每次将mid的数放在偶数位，end的数放在奇数位即可
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] dummy = new int[nums.length];

        int n = nums.length;
        int peak = n - 1;
        int mid = (n - 1) / 2;

        for (int i = 0; i < n; i++) {
            dummy[i] = (i & 1) == 0 ? nums[mid--] : nums[peak--];
        }

        for (int i = 0; i < n; i++) {
            nums[i] = dummy[i];
        }

        return;
    }
}