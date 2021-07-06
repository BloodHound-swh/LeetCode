/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

 

示例：

输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
 

提示：

0 <= nums.length <= 50000
1 <= nums[i] <= 10000
 */

// 首尾双指针
class Solution {
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 == 0) {
                switchNum(nums, left, right);
                right--;
            } else {
                left++;
            }
        }

        return nums;
    }

    public void switchNum(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// LeetCode提交记录中最快的
class Solution {
    public int[] exchange(int[] nums) {

        int i = 0, j = nums.length - 1, temp;
        while(i < j) {
            while((nums[i] & 1) == 1 && i <j ) {
                i++;
            }
            while((nums[j] & 1) == 0 && j > 0) {
                j--;
            }
            if(i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;

    }


}