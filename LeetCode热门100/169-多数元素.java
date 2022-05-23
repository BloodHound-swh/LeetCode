/**
 * 169. 多数元素
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

示例 1：

输入：nums = [3,2,3]
输出：3
示例 2：

输入：nums = [2,2,1,1,1,2,2]
输出：2
 

提示：
n == nums.length
1 <= n <= 5 * 10^4
-10^9 <= nums[i] <= 10^9
 */


// 传统思路，使用HashMap来记录数字与出现次数
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
            if (map.get(num) > nums.length / 2) {
                res = num;
                break;
            }
        }
        return res;
    }
}

// 从第一个数开始，使用count来记录次数，出现相同数字count++，不同时count--，当count为0时，就res制定为下一个数
// 原理：由于众数个数>n/2，且题目限制众数一定存在，所以只要找到不同的两个数就消掉，最后剩下的一定就是众数
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int res = nums[0];
        for (int n : nums) {
            if (count == 0) {
                res = n;
                count++;
            } else {
                if (res == n) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        return res;
    }
}