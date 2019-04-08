/**
 * 最接近的三数之和
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */


// 和3Sum的解法一样。在3Sum中，我们只有找到和目标完全一样的时候才返回
// 但在Closet中，我们要记录一个最小的差值，并同时记录下这个最小差值所对应的和。
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int minDiff = Integer.MAX_VALUE;
        int closestSum = 0;

        while (i < nums.length - 2) {
            int base = nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = base + nums[left] + nums[right];
                int diff = Math.abs(target - sum);
                if (diff < minDiff) {
                    closestSum = sum;
                    minDiff = diff;
                }
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left = moveToRight(nums, left + 1);
                } else {
                    right = moveToLeft(nums, right - 1);
                }
            }
            i = moveToRight(nums, i + 1);
        }

        return closestSum;
    }

    public int moveToRight(int[] nums, int left) {
        while (left < nums.length && nums[left] == nums[left - 1]) {
            left++;
        }
        return left;
    }

    public int moveToLeft(int[] nums, int right) {
        while (right >= 0 && nums[right] == nums[right + 1]) {
            right--;
        }
        return right;
    }
}