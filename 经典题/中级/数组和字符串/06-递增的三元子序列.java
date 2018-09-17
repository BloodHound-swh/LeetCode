/**
 * 递增的三元子序列
给定一个未排序的数组，请判断这个数组中是否存在长度为3的递增的子序列。

正式的数学表达如下:

如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
要求算法时间复杂度为O(n)，空间复杂度为O(1) 。

示例:
输入 [1, 2, 3, 4, 5],
输出 true.

输入 [5, 4, 3, 2, 1],
输出 false.
 */



//使用两个min来维护，取三个区间，当三个区间均存在符合的数字时，即可返回true
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length <= 2) return false;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            //case 3
            if (curr > min2) {
                return true;
            }
            //case 1
            else if (curr < min1) {
                min1 = curr;
            }
            //case 2
            else if (curr > min1 && curr < min2) {
                min2 = curr;
            }
        }
        return false;
    }
}


//更改一下case的顺序，发现运行时间少很多
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int start = Integer.MAX_VALUE;
        int medio = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] < start)
            {
                start = nums[i];
            }
            else if(nums[i] > start && nums[i] < medio)
            {
                medio = nums[i];
            }
            else if(nums[i] > medio)
            {
                return true;
            }
        }
        return false;
    }
}