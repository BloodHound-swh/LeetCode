
// 未看答案，但是没有做到原地算法
class Solution {
    public void rotate(int[] nums, int k) {
        int[] dummy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dummy[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = dummy[i];
        }
    }
}