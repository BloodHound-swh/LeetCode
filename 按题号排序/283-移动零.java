/**
 * 移动零

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。

 */


// 未看答案版
class Solution {
    public void moveZeroes(int[] nums) {
        int p1 = 0;
        int p2 = 0;
        while (p2 < nums.length) {
            if (nums[p2] == 0) {
                p2++;
                continue;
            } else {
                nums[p1] = nums[p2];
                p1++;
                p2++;
            }
        }
        for (int i = p1; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

// 答案一
//用两个指针，一个不停的向后扫，找到非零位置，然后和前面那个指针交换位置即可
class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while(j<nums.length){
            if(nums[j]!=0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            j++;
        }
    }
}

// 答案二
//直接将非零元素向前复制，然后将复制的最后一位之后的数字全部置零，此法可能更加符合题意，因为连temp这个额外空间都不需要了
class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0;
        int len = nums.length;

        for(int i = 0; i < len; i++){
            if(nums[i] != 0){
                nums[j] = nums[i];
                j++;
            }
        }

        for(int i = j; i < len; i++){
            nums[i] = 0;
        }
    }
}