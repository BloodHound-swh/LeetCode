/**
 * 两个数组的交集
给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [9,4]
说明:

输出结果中的每个元素一定是唯一的。
我们可以不考虑输出结果的顺序。
 */

// 使用两个HashSet，可以自动去重
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet();
        Set<Integer> set2 = new HashSet();
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i]))
                set2.add(nums2[i]);
        }
        int[] res = new int[set2.size()];
        int index = 0;
        for (Integer i : set2)
            res[index++] = i;
        return res;
    }
}

// 将第二个数组排序，然后使用二分法看第一个数组的元素是否出现在第二个数组之中
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        int i = 0;
        for (int num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int[] res = new int[set.size()];
        for (int num : set) {
            res[i++] = num;
        }
        return res;
    }

    public boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}