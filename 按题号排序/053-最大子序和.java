/**
 * 最大子序和

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */

// 未看答案没有做出，未能找出何时更新子串和的方法

// 答案一
// 可使用动态规划来解决。时间复杂度为O(n)。假设已知0, .., k的和sum[k]以后，则0, ..., k+1的和sum[k+1]分为以下两种情况：
// 1）若sum[k]>=0，则sum[k+1]=sum[k]+A[k+1]。
// 2）若sum[k]<0，另起一个SubArray，令sum[k+1]=A[k+1]。
// 在计算过程中，使用一个变量maxsum用于存储sum的最大值，一旦出现更大的sum值则更新之，最后返回该变量即可。
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0)
            return 0;
        int currSum = 0;
        int sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            currSum = currSum + nums[i];
            if (currSum > sum)
                sum = currSum;
            if (currSum < 0)
                currSum = 0;
        }
        return sum;
    }
}

// 答案二
// 使用maxCurrent和max来分别储存按顺序序走的当前子串最大值（之前的子串和小于零则重置），和最终的最大值
class Solution {
    public int maxSubArray(int[] nums) {
        int maxCurrent = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxCurrent = Math.max(maxCurrent + nums[i], nums[i]);
            max = Math.max(max, maxCurrent);
        }
        return max;
    }
}

// 答案三，同答案二
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int total_sum = 0;
        int i = 0;
        while (i < nums.length) {
            total_sum += nums[i];
            sum = Math.max(sum, total_sum);
            if (total_sum < 0) {
                total_sum = 0;
            }
            i++;
        }
        return sum;
    }
}