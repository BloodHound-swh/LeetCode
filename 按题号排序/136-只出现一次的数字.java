/**
 * 只出现一次的数字

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4
 */


// 未看答案版
class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1)
                return entry.getKey();
        }
        return 0;

        // 也可以这样遍历map
        // for (Integer key : map.keySet()) {
        //     if (map.get(key) == 1)
        //         return key;
        // }
        // return 0;
    }
}

// 答案一
//先排序，之后使用循环，但是效率不高
// 1. 对数组进行排序。 
// 2. 遍历一次数组，将array[i]分别与array[i-1]和array[i+1]作比较，若都不相同，则找到只出现一次的值。 
// 3. 注意处理第一个数字和最后一个数字的边界情况。
class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        if (nums == null)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums[0] != nums[1])
            return nums[0];
        if (nums[nums.length - 1] != nums[nums.length - 2])
            return nums[nums.length - 1];
        for (int i = 1; i < nums.length - 2; i++) {
            if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1])
                return nums[i];
        }
        return 0;
    }
}

// 因为异或服从交换律，且一个数和0异或还是自己，和自己异或就是0
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            result ^= nums[i];
        }
        return result;
    }
}