/**
 * 283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。

 

示例 1:

输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
示例 2:

输入: nums = [0]
输出: [0]
 

提示:

1 <= nums.length <= 10^4
-231 <= nums[i] <= 2^31 - 1
 */


// 双指针，直接将非零元素向前复制，然后将复制的最后一位之后的数字全部置零
class Solution {
    public void moveZeroes(int[] nums) {
        int p1 = 0;
        int p2 = 0;
        while (p2 < nums.length) {
            if (nums[p2] == 0) {
                p2++;
            } else {
                nums[p1] = nums[p2];
                p2++;
                p1++;
            }
        }

        while (p1 < nums.length) {
            nums[p1] = 0;
            p1++;
        }
    }
}

// 用两个指针，一个不停的向后扫，找到非零位置，然后和前面那个指针交换位置即可
class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            j++;
        }
    }
}