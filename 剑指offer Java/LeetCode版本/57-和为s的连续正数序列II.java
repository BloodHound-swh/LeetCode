/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

 

示例 1：

输入：target = 9
输出：[[2,3,4],[4,5]]
示例 2：

输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 

限制：

1 <= target <= 10^5

 */


// 滑动窗口法
class Solution {
    public int[][] findContinuousSequence(int target) {
        // 滑动窗口的左/右边界
        int left = 1, right = 2;
        List<int[]> res = new ArrayList<>();
        // 滑动窗口中数字的和
        int sum = left + right;
        // 窗口的左边是窗口内的最小数字，只能小于等于target / 2,
        // 因为题中要求的是至少含有两个数
        while (left <= target / 2) {
            if (sum < target) {
                // 如果窗口内的值比较小，右边界继续向右移动，来扩大窗口
                right++;
                sum += right;
            } else if (sum > target) {
                //如果窗口内的值比较大，左边边界往右移动，缩小窗口
                sum -= left;
                left++;
            } else {
                // 如果窗口内的值正好等于target，就把窗口内的值记录下来，然后窗口的左边和右边同时往右移一步
                int[] arr = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    arr[i - left] = i;
                }
                res.add(arr);
                // 注意这里的写法
                sum -= left;
                left++;
                right++;
                sum += right;
            }
        }
        // 把结果转化为数组
        return res.toArray(new int[res.size()][]);
    }
}