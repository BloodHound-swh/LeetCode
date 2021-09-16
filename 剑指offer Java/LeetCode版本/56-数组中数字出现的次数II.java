/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

 

示例 1：

输入：nums = [3,4,3,3]
输出：4
示例 2：

输入：nums = [9,1,7,9,7,9,7]
输出：1
 

限制：

1 <= nums.length <= 10000
1 <= nums[i] < 2^31
 */


// 常规思路，使用map
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }

        return -1;
    }
}

// 巨佬的思路
// https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/shu-ju-jie-gou-he-suan-fa-san-chong-jie-4b9se/
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        // int类型有32位，统计每一位1的个数
        for (int i = 0; i < 32; i++) {
            // 统计第i位中1的个数
            int oneCount = 0;
            for (int j = 0; j < nums.length; j++) {
                oneCount += (nums[j] >>> i) & 1;
            }
            // 如果1的个数不是3的倍数，说明那个只出现一次的数字的二进制位中在这一位是1
            if (oneCount % 3 == 1) {
                res = res | (1 << i);
            }
        }
        return res;
    }
}
