/**
 * 接雨水

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。



上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
 */

// 看每个位置接的水和两边的最高点以及自身高度有什么关系

// 方法一，用两个数组分别记录每一个位置左边的最高高度和右边的最高高度
// 水量就取决于两个最高高度低的那一个减去当前位置自身的高度
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // 定义两个数组，分别存放下标i处左边最高板和右边最高板
        // 由于蓄水决定于最短板，因此求出最短板，然后和height[i]取差值
        int[] left = new int[height.length];
        int[] right = new int[height.length];

        // 求left数组,从左往右开始
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        // 求right数组，从右往左开始
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        int sum = 0, temp = 0;
        for (int i = 1; i < height.length - 1; i++) {
            temp = Math.min(left[i], right[i]) - height[i];
            sum += temp;
        }
        return sum;
    }
}

// 方法二，优化了方法一的空间复杂度
// 使用两个指针，再用两个变量记录左边最高高度和右边最高高度
// 那边小就说明那一边指针所指的位置的雨量确定了
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length < 3)
            return 0;
        int max = 0;
        int leftMax = 0;
        int rightMax = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            leftMax = Math.max(leftMax, height[i]);
            rightMax = Math.max(rightMax, height[j]);
            if (leftMax < rightMax) {
                max += leftMax - height[i];
                i++;
            } else {
                max += rightMax - height[j];
                j--;
            }
        }
        return max;
    }
}