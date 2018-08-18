/**
 * 盛最多水的容器
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。



图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

 

示例:

输入: [1,8,6,2,5,4,8,3,7]
输出: 49

 */



// 使用双指针总两边往中间走，每次移动高度较小的那一条(如果移动高度较高的那条，容量一定会变小)，如果两条边相同，则同时向中间移动
class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        int area = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            area = Math.max(area, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right]) {
                right--;
            } else if (height[left] < height[right]) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        return area;
    }
}

// 思路同方法一，只是简化了两条边相同时的情况，默认相同时，移动左边
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (right > left) {
            max = Math.max(max, (Math.min(height[right], height[left]) * (right - left)));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
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
            while (height[l] <= h && l < r)
                ++l;
            while (height[r] <= h && l < r)
                --r;
        }
        return max;

    }
}