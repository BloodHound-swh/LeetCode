/**
 * 求众数

给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
 */


// 未看答案版，很传统也很简单，使用HashMap
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

// 答案一
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

// 答案二
// 从第一个数开始，使用count来记录次数，出现相同数字count++，不同时count--，当count为0时，就res制定为下一个数
// 原理：由于众数个数>n/2，且题目限制众数一定存在，所以只要找到不同的两个数就消掉，最后剩下的一定就是众数
class Solution {
    public int majorityElement(int[] nums) {
        int result = nums[0], count = 0;
        for (int num : nums) {
            if (count == 0) {
                result = num;
                count++;
            } else {
                if (result == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return result;
    }
}

// 同答案二
class Solution {
    public int majorityElement(int[] nums) {
        int main = nums[0]; // 用于记录主元素，假设第一个是主元素
        int count = 1; // 用于抵消数的个数

        for (int i = 1; i < nums.length; i++) { // 从第二个元素开始到最后一个元素
            if (main == nums[i]) { // 如果两个数相同就不能抵消
                count++; // 用于抵消的数据加1
            } else {
                if (count > 0) { // 如果不相同，并且有可以抵消的数
                    count--; // 进行数据抵消
                } else { // 如果不相同，并且没有可以抵消的数
                    main = nums[i]; // 记录最后不可以抵消的数
                }
            }
        }
        return main;
    }
}

// 答案三
// 取巧，直接将数组排序，取中间的那一个，肯定是众数
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}