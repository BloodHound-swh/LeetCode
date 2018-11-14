
// 这里使用的是模板三，模板一，二在此题不合适
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        if (nums[left] > nums[right]) {
            while (left + 1< right) {
                int mid = left + (right - left) / 2;
                if (nums[left] < nums[mid]) 
                    left = mid;
                else 
                    right = mid;
            }
            return Math.min(nums[left], nums[right]);
        }
        return nums[0];
    }
}