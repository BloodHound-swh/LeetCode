/**
 * 581. 最短无序连续子数组
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。

 

示例 1：

输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
示例 2：

输入：nums = [1,2,3,4]
输出：0
示例 3：

输入：nums = [1]
输出：0
 

提示：

1 <= nums.length <= 10^4
-10^5 <= nums[i] <= 10^5
 

进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 */


// 克隆一个新数组并排序
// 然后我们从左向右找到第一个两数组不同的位置，即为左边界
// 同理从右向左也可以找到右边界
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] newNums = nums.clone();
        Arrays.sort(newNums);
        int left = -1, right = -1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > newNums[i]) {
                left = i;
                break;
            }
        }

        for (int j = len - 1; j >= 0; j--) {
            if (nums[j] < newNums[j]) {
                right = j;
                break;
            }
        }

        return left == -1 ? 0 : right - left + 1;
    }
}

// https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leet-yhlf/
// 我们可以假设把这个数组分成三段，左段和右段是标准的升序数组，中段数组虽是无序的
// 但满足右端+中段的最小值大于左段的最大值，左端+中段的最大值小于右段的最小值
// 因此可以根据上面的推断找到左右边界
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int end = 0, start = len - 1;
        int leftMax = nums[0], rightMin = nums[len - 1];
        for (int i = 0; i < len; i++) {
            if (nums[i] < leftMax) {
                end = i;
            } else {
                leftMax = nums[i];
            }

            if (nums[len - 1 - i] > rightMin) {
                start = len - 1 - i;
            } else {
                rightMin = nums[len - 1 - i];
            }
        }

        return end == 0 ? 0 : end - start + 1;
    }
}