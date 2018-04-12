/*
移动零
给定一个数组 nums, 编写一个函数将所有 0 移动到它的末尾，同时保持非零元素的相对顺序。
例如， 定义 nums = [0, 1, 0, 3, 12]，调用函数之后， nums 应为 [1, 3, 12, 0, 0]。
注意事项:
必须在原数组上操作，不要为一个新数组分配额外空间。
尽量减少操作总数。
 */


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