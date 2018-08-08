/**
 * Longest Increasing Subsequence
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */

 
// 构建最长升序序列长度的数组,找到dp[0]到dp[i-1]中最大的升序序列长度且nums[j]<nums[i]
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] lis = new int[nums.length];
        lis[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], lis[j]);
                }
            }
            lis[i] += 1;
            max = Math.max(max, lis[i]);
        }
        return max;
    }
}

//https://segmentfault.com/a/1190000003819886
// 在1,3,5,2,8,4,6这个例子中，当到6时，我们一共可以有四种
// (1)不同长度
// (2)且保证该升序序列在同长度升序序列中末尾最小
// 的升序序列

// 1
// 1,2
// 1,3,4
// 1,3,5,6
// 如果nums[i]比所有序列的末尾都大，说明有一个新的不同长度序列产生，我们把最长的序列复制一个，并加上这个nums[i]。
// 如果nums[i]比所有序列的末尾都小，说明长度为1的序列可以更新了，更新为这个更小的末尾。
// 如果在中间，则更新那个末尾数字刚刚大于等于自己的那个序列，说明那个长度的序列可以更新了。
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // len + 1表示当前最长的升序序列长度
        int len = 0;
        // tails[i]表示长度为i + 1的升序序列其末尾的数字
        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        // 根据三种情况更新不同升序序列的集合
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < tails[0]) {
                tails[0] = nums[i];
            } else if (nums[i] > tails[len]) {
                tails[++len] = nums[i];
            } else {
                // 如果在中间，则二分搜索, 看每个不同长度序列的最后一位插入
                tails[binarySearch(tails, 0, len, nums[i])] = nums[i];
            }
        }
        return len + 1;
    }

    private int binarySearch(int[] tails, int min, int max, int target) {
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (tails[mid] == target) {
                return mid;
            }
            if (tails[mid] < target) {
                min = mid + 1;
            }
            if (tails[mid] > target) {
                max = mid - 1;
            }
        }
        return min;
    }

    private int binarySearch2(int[] tails, int min, int max, int target) { // 使用标准二分法模版运行速度更快
        while(min + 1 < max) {
            int mid = min + (max - min) / 2;
            if (tails[mid] >= target)
                max = mid;
            else
                min = mid;
        }
        if (tails[min] == target)
            return min;
        return max;
    }
}
