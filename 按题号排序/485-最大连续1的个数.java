/**
 * 最大连续1的个数
给定一个二进制数组， 计算其中最大连续1的个数。

示例 1:

输入: [1,1,0,1,1,1]
输出: 3
解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
注意：

输入的数组只包含 0 和1。
输入数组的长度是正整数，且不超过 10,000。
 */


// 双指针，注意最后到达边界时还需要另外单独计算
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0, right = 0;
        int maxLen = 0;
        while (right < nums.length) {
            if (nums[right] != 1 && nums[left] != 1) {
                right++;
                left++;
            } else if (nums[right] == 1 && nums[left] == 1) {
                right++;
            } else if (nums[left] == 1 && nums[right] != 1) {
                int len = right - left;
                maxLen = Math.max(maxLen, len);
                left = right;
            }
        }
        int len = right - left;
        maxLen = Math.max(maxLen, len);
        return maxLen;
    }
}

// 注意到数组是0-1数组，所以可以用一个计数器
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int cnt = 0;

        for (int num : nums) {
            if (num == 1) {
                cnt++;
            } else {
                res = Math.max(res, cnt);
                cnt = 0;
            }
        }

        return res;
    }
}