/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 

示例 1：

输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
示例 2：

输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
 

限制：

2 <= nums.length <= 10000
 */


// 常规思路，使用Set
class Solution {
    public int[] singleNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        set.add(nums[0]);
        set.add(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }

        int[] res = new int[2];
        int j = 0;
        for (Integer i : set) {
            res[j++] = i;
        }

        return res;
    }
}


// 官方题解, 利用异或操作
// https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/
class Solution {
    public int[] singleNumbers(int[] nums) {
        int p = 0;
        for (int n : nums) {
            p ^= n;
        }

        int div = 1;
        while ((p & div) == 0) {
            div <<= 1;
        }

        int a = 0, b = 0;
        for (int n : nums) {
            if ((n & div) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }

        return new int[] { a, b };
    }
}