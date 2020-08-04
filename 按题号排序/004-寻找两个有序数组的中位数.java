/**
 * 寻找两个有序数组的中位数

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
 */

// 难。。。

// 使用二分法，找较短数组中的取m1个数，来确定较长数组中取m2 = k - m1个数
// 之后左中位数c1 = max(nums1[m1 - 1], nums2[m2 - 1])
// 右中位数c2 = min(nums[m1], nums2[m2])
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);
        
        int k = (n1 + n2 + 1) / 2;
        int l = 0;
        int r = n1;
        
        while (l < r) {
            int m1 = l + (r - l) / 2;
            int m2 = k - m1;
            
            if (nums1[m1] < nums2[m2 - 1])
                l = m1 + 1;
            else 
                r = m1;
        }
        
        int m1 = l;
        int m2 = k - l;
        
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                          m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
        
        if ((n1 + n2) %2 == 1) 
            return c1;
        
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                          m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        
        return (c1 + c2) * 0.5;
    }
}


// 方法二，使用二分法确定两个数组分别需要出多少个数字作为合并数组的左半边（用n1Left和n2Left来表示）
// 比较L1与R2以及L2与R1可以确定二分法的指针移动方向
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);

        int len = n1 + n2;
        int start = 0, end = n1;
        int n1Left, n2Left;

        while (start <= end) {
            n1Left = (start + end) / 2;
            n2Left = (len + 1) / 2 - n1Left;
            double L1 = (n1Left <= 0) ? Integer.MIN_VALUE : nums1[n1Left - 1];
            double L2 = (n2Left <= 0) ? Integer.MIN_VALUE : nums2[n2Left - 1];
            double R1 = (n1Left >= n1) ? Integer.MAX_VALUE : nums1[n1Left];
            double R2 = (n2Left >= n2) ? Integer.MAX_VALUE : nums2[n2Left];
            if (L1 > R2) {
                end = n1Left - 1;
            } else if (L2 > R1) {
                start = n1Left + 1;
            } else {
                if (len % 2 == 0) {
                    return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
                } else {
                    return Math.max(L1, L2);
                }
            }
        }
        return -1;
    }
}