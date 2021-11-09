/**
 * 11. 盛最多水的容器
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器。

 

示例 1：



输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
示例 2：

输入：height = [1,1]
输出：1
示例 3：

输入：height = [4,3,2,1,4]
输出：16
示例 4：

输入：height = [1,2,1]
输出：2
 

提示：

n == height.length
2 <= n <= 105
0 <= height[i] <= 104

 */

// 使用双指针总两边往中间走，每次移动高度较小的那一条(如果移动高度较高的那条，容量一定会变小)
class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int tempArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, tempArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

// 优化了移动过程中不必要的计算，只有在移动后遇到更高的边时才计算容量
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0, h = 0;
        while (l < r) {
            h = Math.min(height[l], height[r]);
            max = Math.max(max, (r - l) * h);
            while (height[l] <= h && l < r) {
                ++l;
            }
            while (height[r] <= h && l < r) {
                --r;
            }
        }
        return max;

    }
}