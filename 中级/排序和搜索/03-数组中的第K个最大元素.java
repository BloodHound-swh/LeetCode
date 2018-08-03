/**
 * 数组中的第K个最大元素 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的
 * 素。
 * 
 * 示例 1:
 * 
 * 输入: [3,2,1,5,6,4] 和 k = 2 输出: 5 示例 2:
 * 
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4 输出: 4 说明:
 * 
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */

// 使用快速排序的思想，每次把大于pivot的数放左边，小于pivot的数放在右边，然后看pos+1的位置与k的关系，大于k，则把右边界设为pos-1，否则就把左边界设为pos+1，等于的话就返回nums[pos]
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
        swap(nums, left, r);
        return r;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 方法一的更简洁的写法
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length)
            return 0;

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int l = left;
            int r = right;
            int pivot = nums[l];
            while (l < r) {
                while (pivot >= nums[r] && l < r) {
                    r--;
                }
                nums[l] = nums[r];
                while (nums[l] >= pivot && l < r) {
                    l++;
                }
                nums[r] = nums[l];
            }
            // l == r
            nums[l] = pivot;
            if (l + 1 == k) {
                return nums[l];
            } else if (l + 1 < k) {
                left = l + 1;
            } else {
                right = l - 1;
            }
        }
        return nums[left];
    }
}

// 遍历数组时将数字加入优先队列（堆），一旦堆的大小大于k就将堆顶元素去除，确保堆的大小为k。遍历完后堆顶就是返回值。
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> p = new PriorityQueue<Integer>();
        for (int i = 0; i < nums.length; i++) {
            p.add(nums[i]);
            if (p.size() > k)
                p.poll();
        }
        return p.poll();
    }
}