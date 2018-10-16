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

// 未看答案版本
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int right = 0;
        int maxOnes = 0;
        while (left < nums.length && right < nums.length) {
            if (nums[left] != 1 && nums[right] != 1) {
                left++;
                right++;
            } else if (nums[left] == 1 && nums[right] == 1) {
                right++;
            } else if (nums[left] == 1 && nums[right] != 1) {
                int currOnes = right - left;
                maxOnes = Math.max(maxOnes, currOnes);
                left = right;
            }
        }
        int currOnes = right - left;
        maxOnes = Math.max(maxOnes, currOnes);
        return maxOnes;
    }
}

// 注意到数组是0-1数组，所以可以用一个计数器
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int cnt = 0;
        for (int num : nums) {
            cnt = (num == 0) ? 0 : cnt + 1;
            res = Math.max(res, cnt);
        }
        return res;
    }
}

// 优化

class Solution {
    public int findMaxConsecutiveOnes(int[] a) {
        int max = 0, s = 0;
        int L = a.length;
        for (int i = 0; i < L; i++) {
            if (a[i] == 1) {
                s++;
            } else {
                max = max > s ? max : s;
                s = 0;
            }
        }
        return max > s ? max : s;
    }
}