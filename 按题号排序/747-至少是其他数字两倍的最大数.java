/**
 * 至少是其他数字两倍的最大数
在一个给定的数组nums中，总是存在一个最大元素 。

查找数组中的最大元素是否至少是数组中每个其他数字的两倍。

如果是，则返回最大元素的索引，否则返回-1。

示例 1:

输入: nums = [3, 6, 1, 0]
输出: 1
解释: 6是最大的整数, 对于数组中的其他整数,
6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
 

示例 2:

输入: nums = [1, 2, 3, 4]
输出: -1
解释: 4没有超过3的两倍大, 所以我们返回 -1.
 

提示:

nums 的长度范围在[1, 50].
每个 nums[i] 的整数范围在 [0, 99].
 */


// 遍历一次数组分别求出最大数字和第二大数字，然后判断一下最大数字是否是第二大数字的两倍即可
class Solution {
    public int dominantIndex(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
                index = i;
            } else if (nums[i] > max2) {
                max2 = nums[i];
            }
        }

        return max1 >= 2 * max2 ? index : -1;
    }
}


// 使用优先队列，比较麻烦，但是很通用
class Solution {
    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]> () {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        for (int i = 0; i< nums.length; i++) {
            pq.offer(new int[] {nums[i], i});
            if (pq.size() > 2) {
                pq.poll();
            }
        }
        
        int[] max2 = pq.poll();
        int[] max1 = pq.poll();
        
        return max1[0] >= 2 * max2[0] ? max1[1] : -1;
    }
}