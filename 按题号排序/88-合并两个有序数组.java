/**
 * 合并两个有序数组

给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:

初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
示例:

输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
 */

// 未看答案版，思想没有错误，但是由于函数是void，所以最后的nums1并没有改变，出现了错误
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = 0;
        int i = 0;
        int j = 0;
        int[] nums = new int[nums1.length];
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                nums[k] = nums1[i];
                i++;
                k++;
            } else {
                nums[k] = nums2[j];
                j++;
                k++;
            }
        }

        if (i >= m) {
            for (; j < n; j++) {
                nums[k] = nums2[j];
                k++;
            }
        } else {
            for (; i < m; i++) {
                nums[k] = nums1[i];
                k++;
            }
        }
        nums1 = nums;
    }
}

// 对上面的进行修改
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = 0;
        int i = 0;
        int j = 0;
        int[] nums = new int[nums1.length];
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                nums[k] = nums1[i];
                i++;
                k++;
            } else {
                nums[k] = nums2[j];
                j++;
                k++;
            }
        }

        if (i >= m) {
            for (; j < n; j++) {
                nums[k] = nums2[j];
                k++;
            }
        } else {
            for (; i < m; i++) {
                nums[k] = nums1[i];
                k++;
            }
        }
        for (int s = 0; s < nums.length; s++) {
            nums1[s] = nums[s];
        }
    }
}

// 答案一
// 因为两个数组都有序，所以从两个数组的最后一位开始比较，将最大的数放在数组1的m+n-1的位置上。
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j])
                nums1[index--] = nums1[i--];
            else
                nums1[index--] = nums2[j--];
        }
        while (i >= 0)
            nums1[index--] = nums1[i--];
        while (j >= 0)
            nums1[index--] = nums2[j--];
    }
}

// 考虑到&&的执行顺序，当i<0的时候必定会跳出循环。
// 当j先小于0时，因为是以数组1位基准，剩下的数字必然已经按顺序排好了。
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j])
                nums1[index--] = nums1[i--];
            else
                nums1[index--] = nums2[j--];
        }
        while (j >= 0)
            nums1[index--] = nums2[j--];
    }
}