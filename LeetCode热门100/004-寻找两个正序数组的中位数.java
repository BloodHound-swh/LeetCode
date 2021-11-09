class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int l = 0, r = n1;
        int k = (n1 + n2 + 1) / 2;

        while (l <= r) {
            int n1Left = (l + r) / 2;
            int n2Left = k - n1Left;
            double L1 = (n1Left <= 0) ? Integer.MIN_VALUE : nums1[n1Left - 1];
            double L2 = (n2Left <= 0) ? Integer.MIN_VALUE : nums2[n2Left - 1];
            double R1 = (n1Left >= n1) ? Integer.MAX_VALUE : nums1[n1Left];
            double R2 = (n2Left >= n2) ? Integer.MAX_VALUE : nums2[n2Left];
            if (L1 > R2) {
                r = n1Left - 1;
            } else if (L2 > R1) {
                l = n1Left + 1;
            } else {
                if ((n1 + n2) % 2 == 0) {
                    return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
                } else {
                    return Math.max(L1, L2);
                }
            }
        }

        return -1;
    }
}