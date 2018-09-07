/**
 * 摆动排序 II
给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

示例 1:

输入: nums = [1, 5, 1, 1, 6, 4]
输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
示例 2:

输入: nums = [1, 3, 2, 2, 3, 1]
输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
说明:
你可以假设所有输入都会得到有效的结果。

进阶:
你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */

// 可以先将数组排序，因为题目要求最终的结果每个奇数位的数字都比它周围两个偶数位的数字大
// 所以只需从mid和end开始，每次将mid的数放在偶数位，end的数放在奇数位即可
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] dummy = new int[nums.length];

        int n = nums.length;
        int peak = n - 1;
        int mid = (n - 1) / 2;

        for (int i = 0; i < n; i++) {
            dummy[i] = (i & 1) == 0 ? nums[mid--] : nums[peak--];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = dummy[i];
        }
        return;
    }
}

// 很复杂，O(n) time with O(1) space。但是个人觉得没有记忆的必要，应用场景太少了
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        this.findMid(nums, 0, nums.length, (nums.length + 1) / 2);
        int mid = nums[(nums.length - 1) / 2];
        // 3-way-partition-to-wiggly in O(n) time with O(1) space.
        int i = 0, j = 0, k = nums.length - 1;
        while (j <= k) {
            if (nums[this.threeWayPartition(j, nums)] > mid) {
                this.swap(this.threeWayPartition(i++, nums), this.threeWayPartition(j++, nums), nums);
            } else if (nums[this.threeWayPartition(j, nums)] < mid) {
                this.swap(this.threeWayPartition(j, nums), this.threeWayPartition(k--, nums), nums);
            } else {
                j++;
            }
        }
    }

    private int threeWayPartition(int i, int[] nums) {
        return (1 + 2 * i) % (nums.length | 1);
    }

    private void findMid(int[] nums, int begin, int end, int k) {
        if (end - begin == 1 && k == 1) {
            return;
        }
        int rand = (int) (Math.random() * (end - begin)) + begin;
        this.swap(rand, begin, nums);
        int pivot = nums[begin];
        int i = begin, j = end;
        while (i < j) {
            while (i < j && nums[--j] >= pivot) {
            }
            nums[i] = nums[j];
            while (i < j && nums[++i] <= pivot) {
            }
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        if (i - begin + 1 > k) {
            this.findMid(nums, begin, i, k);
        } else if (i - begin + 1 < k) {
            this.findMid(nums, i + 1, end, k - (i - begin + 1));
        }
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}