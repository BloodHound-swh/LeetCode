/**
 * 84. 柱状图中最大的矩形
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 

示例 1:



输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10
示例 2：



输入： heights = [2,4]
输出： 4
 

提示：

1 <= heights.length <=105
0 <= heights[i] <= 104
 */


// 首先暴力解法提供优化思路的雏形
// 每次找以当前高度下的最大面积（通过寻找左右边界）
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int maxArea = 0;

        for (int i = 0; i < len; i++) {
            int left = i - 1;
            while (left >= 0) {
                if (heights[left] < heights[i]) {
                    break;
                }
                left--;
            }
            int right = i + 1;
            while (right < len) {
                if (heights[right] < heights[i]) {
                    break;
                }
                right++;
            }
            maxArea = Math.max(maxArea, heights[i] * (right - left - 1));
        }

        return maxArea;
    }
}

// 通过暴力解法可以发现
// 1.如果柱子的高度递减，那么每个柱子的左边界就是第一根柱子，右边界就是本身。
// 2.如果柱子的高度递增，那么每个柱子的右边界就是最后一根柱子，左边界就是本身
// 那么可以通过栈来构建递增序列
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int h = heights[stack.peek()];
                stack.pop();
                // 如果此时栈为空，则代表当前高度为目前遍历长度下最短的高度，直接用h * i，即左边界为0，否则左边界就是自身
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }

            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int h = heights[stack.peek()];
            stack.pop();
            int w = stack.isEmpty() ? len : len - stack.peek() - 1;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }
}


// 优化上述代码
// http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
// 使用一个栈来维护递增的圆柱所对应的索引
// 当第i个圆柱体小于栈顶的圆柱体时，开始向左计算面积，直到栈顶的圆柱体再次小于当前圆柱体
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int start = stack.isEmpty() ? -1 : stack.peek();
                int area = height * (i - start - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }
}