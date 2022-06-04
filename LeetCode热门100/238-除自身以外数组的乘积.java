/**
 * 238. 除自身以外数组的乘积
给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。

题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。

请不要使用除法，且在 O(n) 时间复杂度内完成此题。

 

示例 1:

输入: nums = [1,2,3,4]
输出: [24,12,8,6]
示例 2:

输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]
 

提示：

2 <= nums.length <= 105
-30 <= nums[i] <= 30
保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 

进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */


// 对于某一个数字，如果我们知道其前面所有数字的乘积，同时也知道后面所有的数乘积，那么二者相乘就是我们要的结果
// 所以我们只要分别创建出这两个数组即可，分别从数组的两个方向遍历就可以分别创建出乘积累积数组
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];
        int[] fwd = new int[n];
        int[] bwd = new int[n];

        fwd[0] = 1;
        bwd[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            fwd[i] = fwd[i - 1] * nums[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            bwd[i] = bwd[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            res[i] = fwd[i] * bwd[i];
        }

        return res;
    }
}


// 由于最终的结果都是要乘到结果res中，所以我们可以不用单独的数组来保存乘积，而是直接累积到res
// 我们先从前面遍历一遍，将乘积的累积存入res中，然后从后面开始遍历，用到一个临时变量r，初始化为1，然后每次不断累积，最终得到正确结果
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }

        int r = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            r = nums[i + 1] * r;
            res[i] = res[i] * r;
        }

        return res;
    }
}