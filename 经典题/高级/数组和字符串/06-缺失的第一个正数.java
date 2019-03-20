/**
 * 第一个缺失的正数
给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

示例 1:

输入: [1,2,0]
输出: 3
示例 2:

输入: [3,4,-1,1]
输出: 2
示例 3:

输入: [7,8,9,11,12]
输出: 1
说明:

你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */



// 核心思想就是遍历数组时，将每个元素，和以该元素为下标的元素进行置换，比如第一个元素是3，就将它置换到下标为3的地方，而原本下标为3的地方的元素就换到第一个来。
// 如果换来的元素也是在正确的位置就检查下一个元素，否则继续交换，直到：
// 待交换的两个数是一样的
// 当前位置的元素没有对应的目的地（比如负数，或者超界元素）

// 注意
// 数组是从0开始的，而正数是从1开始的，为了简化映射关系，把数组里所有元素都减了1，最后返回答案时再加1即可。
// 如果最后还没找到，就说明缺失的是最后一个数n
class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i]--;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i && nums[i] >= 0 && nums[i] < nums.length && nums[i] != nums[nums[i]]) { // 最后一个条件是防止[1, 1]
                int tmp = nums[i];
                nums[i] = nums[nums[i]];
                nums[tmp] = tmp; // // 注意这里的nums[tmp] 不要写成了nums[nums[i]]
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i)
                return i + 1;
        }
        return nums.length + 1;
    }
}

// 同方法一，只是没有一开始把所有的数都先减一，也不用考虑[1, 1]需要另加条件了
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0)
            return 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                int pubNum = nums[i];
                while (pubNum >= 1 && pubNum <= nums.length && pubNum != nums[pubNum - 1]) {
                    int tmp = nums[pubNum - 1];
                    nums[pubNum - 1] = pubNum;
                    pubNum = tmp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return nums.length + 1;
    }
}

// 把对应位的值变成负数，最后第i位不是负数，则缺失的正数就是i + 1
// https://www.youtube.com/watch?v=8DqewGsVNkI
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num <= nums.length) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                return i + 1;
        }

        return nums.length + 1;
    }
}