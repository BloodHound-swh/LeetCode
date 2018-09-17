/**
缺失数字
给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。

示例 1:

输入: [3,0,1]
输出: 2
示例 2:

输入: [9,6,4,2,3,5,7,0,1]
输出: 8
说明:
你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 */


//求和法，那么计算出【0...n】的数组的和，再减去待计算数组的和，那么缺少的数就出来了。
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int total = 0;
        for(int i = 0; i < nums.length; i++){
            total += nums[i];
        }
        return sum - total;
    }
}

//同理
class Solution {
    public int missingNumber(int[] nums) {
        // i的范围是0~nums.length-1,算上nums.length（N）就是0~N所有数字 
        int res = nums.length;
        for(int i = 0; i < nums.length; i++){
            res += (i - nums[i]);
        }
        return res;
    }
}


//假设[0,1,3]

//使用连续异或找到确实数字
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for(int i = 1; i <= nums.length; i++){
            res = res ^ i ^ nums[i-1]; // 0 ^ 1 ^ 2 ^ 3 ^ 0 ^ 1 ^ 3 = 2
        }
        return res;
    }
}

class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for(int i = 0; i < nums.length; i++){
            res = res ^ i ^ nums[i]; // 3 ^ 0 ^ 1 ^ 2 ^ 0 ^ 1 ^ 3 = 2
        }
        return res;
    }
}