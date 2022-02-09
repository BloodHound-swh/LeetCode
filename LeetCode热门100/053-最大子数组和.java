/**
 * 53. 最大子数组和
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组 是数组中的一个连续部分。

 

示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1
示例 3：

输入：nums = [5,4,-1,7,8]
输出：23
 

提示：

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

 */


// 要想最大，显然之前的和必须不小于0，否则还不如自己开始重新统计和。
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0)
                sum = 0;
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        
        return maxSum;
    }
}

// 贪心算法
class Solution {
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}