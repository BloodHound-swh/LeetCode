/**
 * 寻找数组的中心索引
给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。

我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。

如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。

示例 1:

输入: 
nums = [1, 7, 3, 6, 5, 6]
输出: 3
解释: 
索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
同时, 3 也是第一个符合要求的中心索引。
示例 2:

输入: 
nums = [1, 2, 3]
输出: -1
解释: 
数组中不存在满足此条件的中心索引。
说明:

nums 的长度范围为 [0, 10000]。
任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 */


// 维护两个变量，left表示index左边的和，right表示index右边的和
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int left = 0;
        int right = sum;
        for (int i = 0; i < nums.length; i++) {
            right -= nums[i];
            if (right == left)
                return i;
            left += nums[i];
        }
        return -1;
    }
}


// 我们先求出数组的总和，然后维护一个当前数组之和curSum，然后对于遍历到的位置，用总和减去当前数字，
// 看得到的结果是否是curSum的两倍，是的话，那么当前位置就是中枢点，返回即可；
// 否则就将当前数字加到curSum中继续遍历，遍历结束后还没返回，说明没有中枢点，返回-1即可
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum = sum + i;
        }
        int s = 0; // 表示index左边的和
        for (int i = 0; i < nums.length; i++) {
            if (s * 2 + nums[i] == sum) {
                return i;
            }
            s = s + nums[i];

        }
        return -1;
    }
}