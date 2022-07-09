/**
 * 739. 每日温度
给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。

 

示例 1:

输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]
示例 2:

输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]
示例 3:

输入: temperatures = [30,60,90]
输出: [1,1,0]
 

提示：

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */


// 暴力解法
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            res[i] = helper(temperatures, i);
        }

        return res;
    }

    public int helper(int[] temperatures, int start) {
        int nowTemp = temperatures[start];
        for (int i = start + 1; i < temperatures.length; i++) {
            if (temperatures[i] > nowTemp) {
                return i - start;
            }
        }

        return 0;
    }
}

// https://leetcode.cn/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode-solution/
// 使用递减栈Descending Stack来做，栈里只有递减元素，思路是这样的，我们遍历数组，如果栈不空，且当前数字大于栈顶元素，那么如果直接入栈的话就不是递减栈了
// 所以我们取出栈顶元素，那么由于当前数字大于栈顶元素的数字，而且一定是第一个大于栈顶元素的数，那么我们直接求出下标差就是二者的距离了
// 然后继续看新的栈顶元素，直到当前数字小于等于栈顶元素停止，然后将数字入栈，这样就可以一直保持递减栈，且每个数字和第一个大于它的数的距离也可以算出来了
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && T[i] > T[s.peek()]) {
                int ori = s.pop();
                res[ori] = i - ori;
            }
            s.push(i);
        }

        return res;
    }
}

// 同上
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // 用来存放下标
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] res = new int[temperatures.length];
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }

        return res;
    }
}