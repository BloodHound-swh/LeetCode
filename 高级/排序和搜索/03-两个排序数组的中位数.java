/**
 * 两个排序数组的中位数
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。

请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。

你可以假设 nums1 和 nums2 不同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

中位数是 (2 + 3)/2 = 2.5
 */

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

        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1], m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
        if ((n1 + n2) % 2 == 1)
            return c1;
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1], m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

        return (c1 + c2) * 0.5;
    }
}

// 同方法一，但是为了套二分法模板，我发现得先考虑一些越界情况，反而不如上面的方法简洁
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);

        int k = (n1 + n2 + 1) / 2;
        int l = 0;
        int r = n1;

        if (n1 == 0) {
            if (n2 != 0) {
                if (n2 % 2 == 0)
                    return ((nums2[k] + nums2[k - 1]) * 0.5);
                else
                    return (nums2[k - 1]);
            }
            return 0;
        }

        while (l + 1 < r) {
            int m1 = l + (r - l) / 2;
            int m2 = k - m1;
            if (nums1[m1] < nums2[m2 - 1])
                l = m1;
            else
                r = m1;
        }
        int m1 = l;
        int m2 = k - l;

        if (nums1[m1] < nums2[m2 - 1]) {
            m1 = r;
            m2 = k - r;
        }

        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1], m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
        if ((n1 + n2) % 2 == 1)
            return c1;
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1], m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

        return (c1 + c2) * 0.5;
    }
}

// 方法二
// 这里有个数学排除思想: 考虑A, B各自的中间点.
// 如果A[mid] < B[mid], 那么 A[0 ~ mid - 1] 就不在 median的range里面, 可以排除.
// divide/conquer就这么来的.
// 具体逻辑看代码, 大致意思就是: 每次都取比较A 和 B [x + k / 2 - 1] 的位置, 然后做range 排除法
// end cases:
// 1. 如果我们发现dfs()里面A或者B的start index溢出了, 那么就是最简单的case: midian一定在另外那个array里面
// 2. 如果 k == 1: 就是找A/B 里面的1st item, 那么做个 `Math.max(A[startA], B[startB])` 就可以
// 总共的数字长度是 (m + n) 而且每次都有一般的内容被删除, 那么time就是 O(log(m + n))


// 上面是老外的解释，下面是我尝试做出的解释
// A[i] <= B[j] --> A[1,...,i] <= B[j]
// 找中位数可以相当于是找第k小的数，令i = k / 2, j = k / 2，则每一次比较A[l + k / 2] 与 B【l + k / 2]，然后删除较小的数组的前k / 2个数即可
// 然后k = k - k / 2
// 直到k = 1
class Solution {
    public double findMedianSortedArrays(int[] numsA, int[] numsB) {
        // Assume edge case is safe
        int n = numsA.length + numsB.length;

        // Handle even/odd cases
        if (n % 2 == 0) {
            return (findKth(numsA, 0, numsB, 0, n / 2) + findKth(numsA, 0, numsB, 0, n / 2 + 1)) / 2.0;
        }
        return (double) findKth(numsA, 0, numsB, 0, n / 2 + 1);
    }

    // Find kth number in two sorted array. k is size
    private int findKth(int[] numsA, int startA, int[] numsB, int startB, int k) {
        // check edge case for startA/startB index
        if (startA >= numsA.length)
            return numsB[startB + k - 1]; // A exhausted, take kth in B
        if (startB >= numsB.length)
            return numsA[startA + k - 1]; // B exhausted, take kth in A

        // handle final condition k == 1. The two elements will be sorted and the
        // smaller one is kth.
        if (k == 1)
            return Math.min(numsA[startA], numsB[startB]);

        // compare and partition at each [x+(k/2-1)] position
        int halfKthOfA = startA + k / 2 - 1 < numsA.length ? numsA[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int halfKthOfB = startB + k / 2 - 1 < numsB.length ? numsB[startB + k / 2 - 1] : Integer.MAX_VALUE;
        if (halfKthOfA < halfKthOfB) {
            return findKth(numsA, startA + k / 2, numsB, startB, k - k / 2);
        } else {
            return findKth(numsA, startA, numsB, startB + k / 2, k - k / 2);
        }
    }
}