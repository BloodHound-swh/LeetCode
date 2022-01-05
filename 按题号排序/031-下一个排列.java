/**
 * 下一个排列
实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须 原地 修改，只允许使用额外常数空间。

 

示例 1：

输入：nums = [1,2,3]
输出：[1,3,2]
示例 2：

输入：nums = [3,2,1]
输出：[1,2,3]
示例 3：

输入：nums = [1,1,5]
输出：[1,5,1]
示例 4：

输入：nums = [1]
输出：[1]
 

提示：

1 <= nums.length <= 100
0 <= nums[i] <= 100
 */

// 先找出第一个索引 k 满足nums[k] < nums[k+1]，如果不存在，就翻转整个数组
// 再找出另一个索引 l 满足nums[l] > nums[k]
// 交换nums[l]和nums[k]
// 最后翻转nums[k+1:]
// 比如 nums = [1,2,7,4,3,1]，下一个排列是什么？
// 我们找到第一个索引是 nums[1] = 2
// 再找到第二个索引是 nums[4] = 3
// 交换，nums = [1,3,7,4,2,1]
// 翻转，nums = [1,3,1,2,4,7]
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            // 其实这里的j >= 0也可以省去
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void reverse(int[] nums, int start) {
        int l = start, r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            r--;
            l++;
        }
    }
}