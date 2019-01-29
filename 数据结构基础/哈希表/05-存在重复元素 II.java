/**
 * 存在重复元素 II
给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

示例 1:

输入: nums = [1,2,3,1], k = 3
输出: true
示例 2:

输入: nums = [1,0,1,1], k = 1
输出: true
示例 3:

输入: nums = [1,2,3,1,2,3], k = 2
输出: false

 */


// 很简单，因为没有说明数组的大小范围，所以用二重循环的话时间复杂度可能会很大
// 所以就使用了map
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else {
                if (i - map.get(nums[i]) <= k)
                    return true;
                map.put(nums[i], i);
            }
        }
        return false;
    }
}

// 同上
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <0)
            return false;
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){//exist same value
                int index = map.get(nums[i]);
                if(i-index<=k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}