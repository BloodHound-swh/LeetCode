/**
 * 215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

 

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
 

提示：

1 <= k <= nums.length <= 10^4
-104 <= nums[i] <= 10^4
 */


// 常规思路，使用优先队列，控制其长度
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int n : nums) {
            q.offer(n);
            if (q.size() > k) {
                q.poll();
            }
        }

        return q.poll();
    }
}

// 使用快速排序的思想，每次把大于pivot的数放左边，小于pivot的数放在右边。
// 然后看pos+1的位置与k的关系，大于k，则把右边界设为pos-1，否则就把左边界设为pos+1，等于的话就返回nums[pos]
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            int pos = quicksort(nums, left, right);
            if (pos + 1 == k) {
                return nums[pos];
            } else if (pos + 1 > k) {
                right = pos - 1;
            } else {
                left = pos + 1;
            }
        }
    }

    public int quicksort(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1;
        int r = right;
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                swap(nums, l++, r--);
            }
            if (nums[l] >= pivot)
                l++;
            if (nums[r] <= pivot)
                r--;
        }
        swap(nums, left, r); // 在l == r 时，如果此时的nums[r] < pivot时，在while中r--了，所以可以保证这时swap依然是左大右小
        return r;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}